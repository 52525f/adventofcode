package aoc2015

import java.io.File
import java.util.*

fun main(args: Array<String>) {
    val moleculeStr = "CRnCaSiRnBSiRnFArTiBPTiTiBFArPBCaSiThSiRnTiBPBPMgArCaSiRnTiMgArCaSiThCaSiRnFArRnSiRnFArTiTiBFArCaCaSiRnSiThCaCaSiRnMgArFYSiRnFYCaFArSiThCaSiThPBPTiMgArCaPRnSiAlArPBCaCaSiRnFYSiThCaRnFArArCaCaSiRnPBSiRnFArMgYCaCaCaCaSiThCaCaSiAlArCaCaSiRnPBSiAlArBCaCaCaCaSiThCaPBSiThPBPBCaSiRnFYFArSiThCaSiRnFArBCaCaSiRnFYFArSiThCaPBSiThCaSiRnPMgArRnFArPTiBCaPRnFArCaCaCaCaSiRnCaCaSiRnFYFArFArBCaSiThFArThSiThSiRnTiRnPMgArFArCaSiThCaPBCaSiRnBFArCaCaPRnCaCaPMgArSiRnFYFArCaSiThRnPBPMgAr"
    val lines = File("src/main/resources/day19.txt").readLines()
    val replacements = Replacement(lines)
    val replacementSet = Molecule(moleculeStr, replacements).applyReplacements()

    println("Part 1")
    println("Distinct molecules = " + replacementSet.size)
}

class Replacement(list: List<String>) {
    private val theMap: MutableMap<String, MutableList<String>> = mutableMapOf()

    init {
        list.map { parseReplacement(it) }.forEach {
            theMap.put(it.first,
                    theMap.getOrDefault(it.first, mutableListOf()).apply { add(it.second) })
        }
    }

    fun forString(s: String): List<String> {
        return theMap[s] ?: emptyList()
    }

    private fun parseReplacement(str: String): Pair<String, String> {
        val sides = str.split(" => ")
        return Pair(sides[0], sides[1])
    }
}

class Molecule(str: String, private val replacements: Replacement) {
    private val elements: List<String>

    init {
        elements = tokenize(str)
    }

    private fun tokenize(str: String): List<String> {
        return when {
            str.isEmpty() -> emptyList()
            str.length == 1 -> listOf(str)
            !this.replacements.forString(str.substring(0, 2)).isEmpty() -> listOf(str.substring(0, 2)) + tokenize(str.substring(2))
            else -> listOf(str.substring(0, 1)) + tokenize(str.substring(1))
        }
    }

    // first try: bfs -- too slow!
    fun stepsToTransformTo(to: Molecule): Int {
        val queue = ArrayDeque<Molecule>()
        var steps = 1
        queue.add(this)
        queue.add(Molecule("", replacements))
        while (!queue.isEmpty()) {
            val current = queue.remove()
            if (current.isEmpty()) {
                steps++
                println("Steps = " + steps + " Queue size = " + queue.size)
                queue.add(Molecule("", replacements))
            } else {
                for (molecule in current.applyReplacements()) {
                    when (molecule) {
                        to -> return steps
                        else -> queue.add(molecule)
                    }
                }
            }
        }
        return 0
    }

    fun applyReplacements(): Set<Molecule> {
        var result = setOf<Molecule>()
        for (i in 0 until elements.size) {
            replacements.forString(elements[i]).forEach {
                val replaced = (elements.subList(0, i) + it + elements.subList(i+1, elements.size))
                        .joinToString("")
                result += Molecule(replaced, replacements)
            }
        }
        return result
    }

    fun isEmpty(): Boolean {
        return elements.isEmpty()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Molecule

        if (elements != other.elements) return false

        return true
    }

    override fun hashCode(): Int {
        return elements.hashCode()
    }
}
