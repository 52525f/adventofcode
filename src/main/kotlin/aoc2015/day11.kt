package aoc2015

fun main(args: Array<String>) {
    val startPassword = "vzbxkghb"
    val next = getNextPassword(startPassword)
    println(next)
    val afterThat = getNextPassword(next)
    println(afterThat)
}

val letters = "abcdefghijklmnopqrstuvwxyz"
val next = letters.zip(letters.substring(1) + "a").toMap()

fun getNextPassword(password: String): String {
    var next = incrementPassword(password)
    while (!isValid(next)) {
        next = incrementPassword(next)
    }
    return next
}

fun isValid(password: String): Boolean {
    return hasTwoPairs(password) && hasStraight(password) && !containsInvalidChar(password)
}

fun incrementPassword(s: String): String {
    return if (s.isEmpty()) {
        ""
    } else {
        val front = s.substring(0, s.length - 1)
        val last = s.last()
        if (last == 'z') {
            incrementPassword(front) + next[last]
        } else {
            front + next[last]
        }
    }
}


fun hasTwoPairs(s: String): Boolean {
    return s.contains(Regex("(\\w)\\1+.*(\\w)\\2+"))
}

fun hasStraight(s: String): Boolean {
    return when {
        s.length < 3 -> false
        isStraight(s.substring(0, 3)) -> true
        else -> hasStraight(s.substring(1))
    }
}

private fun isStraight(s: String): Boolean {
    return when {
        s.length < 3 -> false
        else -> s[0].inc() == s[1] && s[1].inc() == s[2]
    }
}

fun containsInvalidChar(s: String): Boolean {
    return s.contains(Regex("[ilo]"))
}