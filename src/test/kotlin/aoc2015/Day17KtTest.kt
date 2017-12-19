package aoc2015

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class Day17KtTest {
    @Test
    fun getsCombinationsOfEmptyList() {
        val list = emptyList<Int>()
        val combinations = findCombinations(list, 10)
        assertTrue(combinations.isEmpty())
    }

    @Test
    fun getsCombinationsOfOneItem() {
        val list = listOf(10)
        val combinations = findCombinations(list, 10)
        assertEquals(1, combinations.size)
        assertEquals(listOf(10), combinations[0])
    }

    @Test
    fun getsCombinationsOfOneWrongSizeItem() {
        val list = listOf(10)
        val combinations = findCombinations(list, 5)
        assertTrue(combinations.isEmpty())
    }

    @Test
    fun getsCombinationsOfTwo() {
        val list = listOf(10, 5)
        val combinations = findCombinations(list, 15)
        assertEquals(1, combinations.size)
        assertEquals(listOf(10, 5), combinations[0])
    }

    @Test
    fun solvesExample() {
        // 20, 15, 10, 5, and 5
        val list = listOf(20, 15, 10, 5, 5)
        val combinations = findCombinations(list, 25)
        assertEquals(4, combinations.size)
    }

    @Test
    fun findsCombinationsUsingMinimumNumberOfContainers() {
        val list = listOf(20, 15, 10, 5, 5)
        val combinations = solvePart2(list, 25)
        assertEquals(3, combinations.size)
    }
}