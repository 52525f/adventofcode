package aoc2015

fun main(args: Array<String>) {
    val directions = readLine()
    val santas = getEveryCharWhere(directions, ::isEven)
    val robots = getEveryCharWhere(directions, ::isOdd)
    print(getHouses(santas).union(getHouses(robots)).size)
}

fun getEveryCharWhere(str: String?, condition: (index: Int) -> Boolean): String {
    var result = ""
    str?.forEachIndexed { index, c ->
        if(condition(index)) {
            result += c
        }
    }
    return result
}

fun isEven(num: Int): Boolean {
    return num % 2 == 0
}

fun isOdd(num: Int): Boolean {
    return !isEven(num)
}

fun getHouses(directions: String?): Set<Pair<Int,Int>> {
    val initial = Pair(0, 0)
    val houses = mutableSetOf(initial)
    var current = initial
    directions?.forEach { c ->
        val (x, y) = current
        when(c) {
            '^' -> current = Pair(x, y+1)
            'v' -> current = Pair(x, y-1)
            '>' -> current = Pair(x+1, y)
            '<' -> current = Pair(x-1, y)
        }
        houses.add(current)
    }
    return houses
}