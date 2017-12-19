package aoc2015

import java.io.File

fun main(args: Array<String>) {
    val input = File("src/main/resources/day1.txt").readText()

    println(solvePart1(input))
    println(solvePart2(input))
}

fun solvePart1(s: String): Int {
    return s.toCharArray().map { c -> toValue(c) }.sum()
}

fun toValue(c: Char): Int = when(c) {
    '(' -> 1
    ')' -> -1
    else -> 0
}

fun solvePart2(s: String): Int {
    var floor = 1
    s.forEachIndexed { index, c ->
        when(c) {
            '(' -> floor++
            ')' -> floor--
        }

        if(floor == -1) return index
    }
    return 0
}
