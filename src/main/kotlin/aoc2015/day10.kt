package aoc2015

fun lookSay(str: String): String {
    if (str.isEmpty())
        return ""
    var count = 1
    var prev = str[0]

    val sb = StringBuilder()
    for (c in str.substring(1)) {
        if (c == prev) {
            count++
        } else {
            sb.append(count)
            sb.append(prev)
            count = 1
            prev = c
        }
    }
    sb.append(count)
    sb.append(prev)
    return sb.toString()
}

fun main(args: Array<String>) {
    var sequence = "3113322113"
    for (i in 0 until 50) {
        sequence = lookSay(sequence)
    }
    println(sequence.length)
}