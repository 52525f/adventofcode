package aoc2015

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class Day18KtTest {
    @Test
    fun countsNeighbors() {
        val grid = Grid.parseConfiguration(listOf(".X.", "X..", ".X."))
        assertEquals(3, grid.countLitNeighbors(1,1))
    }

    @Test
    fun countsLights() {
        val grid = Grid.parseConfiguration(listOf("XX.", "XXX", ".X."))
        assertEquals(6, grid.countLights())
    }

    @Test
    fun getsNextState() {
        val grid = Grid.parseConfiguration(listOf(".#.#.#",
                "...##.",
                "#....#",
                "..#...",
                "#.#..#",
                "####.."))
        val next = grid.nextState()
        println(next.toString())
        assertEquals(11, next.countLights())
    }

    @Test
    fun getsExampleStateAfter4Steps() {
        var grid = Grid.parseConfiguration(listOf(".#.#.#",
                "...##.",
                "#....#",
                "..#...",
                "#.#..#",
                "####.."))
        for (i in 1..4) {
            grid = grid.nextState()
        }
        println(grid.toString())
        assertEquals(4, grid.countLights())
    }

    @Test
    fun solvesPart2Example() {
        var grid = Grid.parseConfigurationForPart2(listOf(".#.#.#",
                "...##.",
                "#....#",
                "..#...",
                "#.#..#",
                "####.."))
        for (i in 1..5) {
            grid = grid.nextStatePart2()
        }
        println(grid.toString())
        assertEquals(17, grid.countLights())
    }
}