package aoc2015

fun main(args: Array<String>) {
    var nice = 0
    var nice2 = 0
    do {
        val str = readLine() ?: break
        if (partOneIsNice(str))
            nice++
        if (partTwoIsNice(str))
            nice2++
    } while(str.isNotEmpty())
    println("Part 1 nice = " + nice)
    println("Part 2 nice = " + nice2)
}

fun partOneIsNice(str: String): Boolean {
    val vowels = setOf('a', 'e', 'i', 'o', 'u')
    var numVowels = 0
    var prevChar = '-'
    var hasRepeatedChar = false
    for (c in str) {
        if (vowels.contains(c)) {
            numVowels++
        }
        if (c == prevChar) {
            hasRepeatedChar = true
        }
        if (badCombination(prevChar, c)) {
            return false
        }
        prevChar = c
    }
    return hasRepeatedChar && numVowels >= 3
}

fun badCombination(prevChar: Char, currentChar: Char): Boolean {
    return (prevChar == 'a' && currentChar == 'b') ||
            (prevChar == 'c' && currentChar == 'd') ||
            (prevChar == 'p' && currentChar == 'q') ||
            (prevChar == 'x' && currentChar == 'y')
}


fun partTwoIsNice(str: String): Boolean {
    return hasDuplicatePair(str) && hasSeparatedRepeatedLetter(str)
}

fun hasDuplicatePair(str: String): Boolean {
    for (i in 0 until str.length-1) {
        val pair = str.slice(i..i+1)
        if (str.substring(i+2).contains(pair))
            return true
    }
    return false
}

fun hasSeparatedRepeatedLetter(str: String): Boolean {
    return (0 until str.length - 2).any { str[it] == str[it +2] }
}
