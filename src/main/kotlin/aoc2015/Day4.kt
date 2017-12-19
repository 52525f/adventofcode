package aoc2015

import java.security.MessageDigest

fun main(args: Array<String>) {
    val secretKey = "yzbqklnj"
    println("Part 1: " + solvePartOne(secretKey))
    println("Part 2: " + solvePartTwo(secretKey))
}

fun solvePartOne(secretKey: String): Int {
    for(i in 1..Int.MAX_VALUE) {
        val input = secretKey + i
        val md5 = hashMd5(input)
        if (md5.startsWith("00000")) {
            return i
        }
    }
    return -1
}

fun solvePartTwo(secretKey: String): Int {
    for(i in 1..Int.MAX_VALUE) {
        val input = secretKey + i
        val md5 = hashMd5(input)
        if (md5.startsWith("000000")) {
            return i
        }
    }
    return -1
}

fun hashMd5(input: String): String {
    val bytes = MessageDigest
            .getInstance("MD5")
            .digest(input.toByteArray())
    return bytesToHex(bytes)
}

fun bytesToHex(bytes: ByteArray): String {
    val hexChars = "0123456789ABCDEF"
    val result = StringBuilder(bytes.size * 2)
    bytes.forEach {
        val i = it.toInt()
        result.append(hexChars[i shr 4 and 0xf])
        result.append(hexChars[i and 0xf])
    }
    return result.toString()
}