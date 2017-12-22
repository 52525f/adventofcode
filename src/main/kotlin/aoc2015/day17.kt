package aoc2015

import java.io.File

fun main(args: Array<String>) {
    val list = File("src/main/resources/day17.txt").readLines().map { it.toInt() }
    println("Part 1:")
    println("Count = " + findCombinations(list, 150).count())

    println("Part 2:")
    println("Count = " + solvePart2(list, 150).count())
}

fun findCombinations(list: List<Int>, n: Int): List<List<Int>> {
    return when {
        list.isEmpty() -> emptyList()
        list.size == 1 && list[0] == n -> listOf(listOf(n))
        list.size == 1 -> emptyList()
        else -> {
            val head = list.first()
            val rest = list.subList(1, list.size)
            when {
                head > n -> findCombinations(rest, n)
                head == n -> listOf(listOf(head)) + findCombinations(rest, n)
                else -> {
                    val combinations = findCombinations(rest, n - head)
                    combinations.map { listOf(head) + it } + findCombinations(rest, n)
                }
            }
        }
    }
}

// number of combinations using the minimum number of containers
fun solvePart2(list: List<Int>, n: Int): List<List <Int>> {
    val combinations = findCombinations(list, n)
    return combinations.filter { it.size == combinations.map { it.size }.min()}
}