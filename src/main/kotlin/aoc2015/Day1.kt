package aoc2015

import java.util.Arrays
import java.util.Scanner

object Day1 {
    @JvmStatic
    fun main(args: Array<String>) {
        val sc = Scanner(System.`in`)
        val input = sc.next()
        var floor = 0

        println(solvePart2(input))
//        var pos = 1;
//        for (c in input.toCharArray()) {
//            if (c == '(') {
//                floor++
//            } else if (c == ')') {
//                floor--
//            }
//            if (floor == -1)
//                println(pos)
//            pos++
//        }
//        val result = input.toCharArray().map { c -> toValue(c) }.sum()
//        println(floor)
    }

    fun toValue(c: Char): Int = when(c) {
        '(' -> 1
        ')' -> -1
        else -> 0
    }

    fun solvePart2(s: String): Int {
        var floor = 0
        s.forEachIndexed { index, c ->
            when(c) {
                '(' -> floor++
                ')' -> floor--
            }

            if(floor == -1) return index
        }
        return 0
    }
}
