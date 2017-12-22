package aoc2015

import java.io.File



fun main(args: Array<String>) {
    val aunts = File("src/main/resources/day16.txt").readLines().map { buildTraitMap(it) }
    val clues = mapOf("children" to 3,
            "cats" to 7,
            "samoyeds" to 2,
            "pomeranians" to 3,
            "akitas" to 0,
            "vizslas" to 0,
            "goldfish" to 5,
            "trees" to 3,
            "cars" to 2,
            "perfumes" to 1)
    println("Part 1")
    val part1Winners = aunts.withIndex().filter { satisfiesClues(it.value, clues) }
    println(part1Winners.map { Pair(it.index+1, it.value)})

    println("Part 2")
    val part2Winners = aunts.withIndex().filter { satisfiesPart2Clues(it.value, clues) }
    println(part2Winners.map { Pair(it.index+1, it.value)})

}

fun buildTraitMap(s: String): Map<String, Int> {
    val pairs = s.substring(s.indexOf(':') + 1)
            .filter { !it.isWhitespace() }
            .split(Regex("[:,]"))
            .partition { !it.matches(Regex("[\\d]+")) }

    return pairs.first
            .zip(pairs.second.map { it.toInt() })
            .toMap()
}


fun satisfiesClues(aunt: Map<String, Int>, clues: Map<String, Int>): Boolean {
    return aunt.entries.none { clues[it.key] != it.value }
}

fun satisfiesPart2Clues(aunt: Map<String, Int>, clues: Map<String, Int>): Boolean {
    return aunt.entries.none {
        when {
            it.key == "cats" || it.key == "trees" -> clues[it.key]!! >= it.value
            it.key == "pomeranians" || it.key == "goldfish" -> clues[it.key]!! <= it.value
            else -> clues[it.key] != it.value
        }
    }
}

