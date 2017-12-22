package aoc2015

import java.io.File
import java.io.InputStream

fun main(args: Array<String>) {
    val jsonLines = mutableListOf<String>()
    val inputStream: InputStream = File("src/main/resources/day12.json").inputStream()
    inputStream.bufferedReader().useLines { lines -> lines.forEach { jsonLines.add(it) } }
    println(jsonLines.map { sumNums(it) }.sum())
}

fun sumNums(s: String): Int {
    val p = Regex("[\\-\\d]+")
    return p.findAll(s).map{ it.value.toInt() }.sum()
}