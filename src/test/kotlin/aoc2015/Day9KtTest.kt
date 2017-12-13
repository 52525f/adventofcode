package aoc2015

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class Day9KtTest {

    @Test
    fun generatesPermutations() {
        val list = permutations(listOf("London", "Dublin", "Belfast"))
        assertEquals(6, list.size)
    }

    @Test
    fun addsRoute() {
        val adjList = Route()
        adjList.addRoute("London", "Dublin", 464)
        assertEquals(464, adjList.getNeighbors("London")["Dublin"])
        assertEquals(464, adjList.getNeighbors("Dublin")["London"])
    }

    @Test
    fun parsesRoutes() {
        val routes = listOf("London to Dublin = 464", "London to Belfast = 518", "Dublin to Belfast = 141")

        val adjList = parseInput(routes)
        assertEquals(2, adjList.getNeighbors("London").size)
        assertEquals(2, adjList.getNeighbors("Belfast").size)
    }

    @Test
    fun findsShortestRoute() {
        val adjList = Route()
        adjList.addRoute("London", "Dublin", 464)
        adjList.addRoute("London", "Belfast", 518)
        adjList.addRoute("Dublin", "Belfast", 141)
        val shortest = adjList.findShortestRoute()
        assertEquals(3, shortest.size)
    }
}