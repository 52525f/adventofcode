package aoc2015

import java.lang.Math.sqrt


fun main(args: Array<String>) {
    var n = 1
    while (getFactors(n).map { it * 10 }.sum() < 33100000) {
        n++
    }
    println("Part 1")
    println(n)

    n = 1
    while(getFactors(n).filter { it * 50 >= n }.map { it * 11 }.sum() < 33100000) {
        n++
    }
    println("Part 2")
    println(n)
}

fun getFactors(n: Int, start: Int = 1): Set<Int> {
    return when {
        n == 1 -> setOf(n)
        n % start == 0 -> setOf(n / start, start) + getFactors(n, start+1)
        start < sqrt(n.toDouble()) -> getFactors(n, start + 1)
        else -> setOf()
    }
}