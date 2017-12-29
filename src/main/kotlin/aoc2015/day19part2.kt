package aoc2015

import java.io.File
import java.lang.Math.random


fun main(args: Array<String>) {
    val molecule = "CRnCaSiRnBSiRnFArTiBPTiTiBFArPBCaSiThSiRnTiBPBPMgArCaSiRnTiMgArCaSiThCaSiRnFArRnSiRnFArTiTiBFArCaCaSiRnSiThCaCaSiRnMgArFYSiRnFYCaFArSiThCaSiThPBPTiMgArCaPRnSiAlArPBCaCaSiRnFYSiThCaRnFArArCaCaSiRnPBSiRnFArMgYCaCaCaCaSiThCaCaSiAlArCaCaSiRnPBSiAlArBCaCaCaCaSiThCaPBSiThPBPBCaSiRnFYFArSiThCaSiRnFArBCaCaSiRnFYFArSiThCaPBSiThCaSiRnPMgArRnFArPTiBCaPRnFArCaCaCaCaSiRnCaCaSiRnFYFArFArBCaSiThFArThSiThSiRnTiRnPMgArFArCaSiThCaPBCaSiRnBFArCaCaPRnCaCaPMgArSiRnFYFArCaSiThRnPBPMgAr"
    val target = "e"
    val replacements = File("src/main/resources/day19.txt").readLines()
            .map {
                val parts = it.split(" => ")
                Pair(parts[1], parts[0])
            }
            .sortedBy { it.first.length }
            .reversed()

    var steps = 0
    var current = molecule
    while (current != target) {
        val lastSteps = steps
        replacements.forEach {
            if (current.contains(it.first) && random() > 0.5) {
                current = current.replaceFirst(it.first, it.second)
                steps++
            }
        }

        if (steps == lastSteps) { // stuck, so restart
            current = molecule
            steps = 0
        }
    }
    println("Steps = $steps")
}
