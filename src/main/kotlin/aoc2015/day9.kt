package aoc2015

import java.io.File
import java.io.InputStream

fun main(args: Array<String>) {
    val routes = mutableListOf<String>()
    val inputStream: InputStream = File("src/main/resources/day9.txt").inputStream()
    inputStream.bufferedReader().useLines { lines -> lines.forEach { routes.add(it) } }
    val adjList = parseInput(routes)

    val shortestRoute = adjList.findShortestRoute()
    println("Part 1: Shortest")
    println(shortestRoute.joinToString(" -> ") + " = " + adjList.getDistance(shortestRoute))

    val longestRoute = adjList.findLongestRoute()
    println("Part 2: Longest")
    println(longestRoute.joinToString(" -> ") + " = " + adjList.getDistance(longestRoute))
}

fun permutations(lst: List<String>): List<List<String>> {
    if (lst.isEmpty()) {
        return ArrayList()
    } else if (lst.size == 1) {
        return listOf(lst)
    }

    val cur = ArrayList<List<String>>()
    lst.forEach { elem ->
        val remainder = lst - elem
        permutations(remainder).mapTo(cur) { listOf(elem) + it }
    }
    return cur
}


class Route {
    private val routeMap = mutableMapOf<String, MutableMap<String, Int>>()

    fun addRoute(from: String, to: String, distance: Int) {
        routeMap.put(from, routeMap.getOrDefault(from, mutableMapOf()).apply { put(to, distance) })
        routeMap.put(to, routeMap.getOrDefault(to, mutableMapOf()).apply { put(from, distance) })
    }

    fun getNeighbors(city: String): Map<String, Int> {
        return routeMap[city] ?: mapOf()
    }

    fun findShortestRoute(): List<String> {
        var min = Int.MAX_VALUE
        var bestRoute = listOf<String>()
        permutations(routeMap.keys.toList()).forEach { cities ->
            val distance = getDistance(cities)
            if (distance < min) {
                min = distance
                bestRoute = cities
            }
        }
        return bestRoute
    }

    fun findLongestRoute(): List<String> {
        var max = Int.MIN_VALUE
        var bestRoute = listOf<String>()
        permutations(routeMap.keys.toList()).forEach { cities ->
            val distance = getDistance(cities)
            if (distance > max && distance != Int.MAX_VALUE) {
                max = distance
                bestRoute = cities
            }
        }
        return bestRoute
    }

    fun getDistance(cities: List<String>): Int {
        if (cities.isEmpty() || cities.size == 1)
            return 0

        val first = cities[0]
        val second = cities[1]
        val rest = cities.subList(1, cities.size)
        if (routeMap[first]!!.containsKey(second)) {
            return getDistanceBetween(first, second) + getDistance(rest)
        }
        return Int.MAX_VALUE
    }

    private fun getDistanceBetween(first: String, second: String): Int {
        return getNeighbors(first)[second]!!
    }
}

fun parseInput(routes: List<String>): Route {
    val adjList = Route()
    routes.forEach {
        val tokens = it.split(" ")
        val from = tokens[0]
        val to = tokens[2]
        val distance = tokens[4].toInt()
        adjList.addRoute(from, to, distance)
    }
    return adjList
}
