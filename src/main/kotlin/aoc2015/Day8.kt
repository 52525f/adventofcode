package aoc2015

import java.io.File
import java.io.InputStream

fun main(args: Array<String>) {
    val strings = mutableListOf<String>()
    val inputStream: InputStream = File("src/main/resources/day8.txt").inputStream()
    inputStream.bufferedReader().useLines { lines -> lines.forEach { strings.add(it) } }
    println("Part 1 = " + (getTotalCodeChars(strings) - getTotalStrChars(strings)))
    println("Part 2 = " + (getTotalPart2EncodedChars(strings) - getTotalCodeChars(strings)))
}

fun getTotalCodeChars(strs: List<String>): Int {
    return strs.map { it.length }.sum()
}

fun getTotalStrChars(strs: List<String>): Int {
    return strs.map { getStringChars(it)}.sum()
}

fun getStringChars(s: String): Int {
    return s.replace(Regex("^\""), "")
            .replace(Regex("\"$"), "")
            .replace(Regex("\\\\\""), "\"")
            .replace(Regex("\\\\\\\\"), "1")
            .replace(Regex("\\\\x[a-z0-9]{2}"), "x")
            .length
}

fun getTotalPart2EncodedChars(strs: List<String>): Int {
    return strs.map { getEncodedStringChars(it)}.sum()
}

fun getEncodedStringChars(s: String): Int {
    return s.replace(Regex("^\""), "---")
            .replace(Regex("\"$"), "---")
            .replace(Regex("\\\\\""), "----")
            .replace(Regex("\\\\\\\\"), "----")
            .replace(Regex("\\\\x[a-z0-9]{2}"), "-----")
            .length
}

