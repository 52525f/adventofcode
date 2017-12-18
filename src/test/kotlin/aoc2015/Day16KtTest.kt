package aoc2015

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class Day16KtTest {
    @Test
    fun buildsTraitMapFromString() {
        val aunt = "Sue 1: goldfish: 6, trees: 9, akitas: 0"
        val map = buildTraitMap(aunt)
        assertEquals(6, map["goldfish"])
        assertEquals(9, map["trees"])
        assertEquals(0, map["akitas"])
    }

    @Test
    fun testsWhetherAuntSatisfiesClues() {
        val aunt1 = mapOf("goldfish" to 5, "trees" to 3, "akitas" to 0)
        val aunt2 = mapOf("goldfish" to 6, "trees" to 3, "akitas" to 0)
        val clues = mapOf("children" to 3,
                "cats" to 7,
                "samoyeds" to 2,
                "pomeranians" to 3,
                "akitas" to 0,
                "vizslas" to 0,
                "goldfish" to 5,
                "trees" to 3,
                "cars" to 2,
                "perfumes" to 1)
        assertTrue(satisfiesClues(aunt1, clues))
        assertFalse(satisfiesClues(aunt2, clues))
    }

    @Test
    fun checksPart2Conditions() {
        val aunt1 = mapOf("goldfish" to 2, "trees" to 4, "akitas" to 0)
        val aunt2 = mapOf("goldfish" to 6, "trees" to 3, "akitas" to 0)
        val aunt3 = mapOf("cars" to 10, "akitas" to 6, "perfumes" to 7)
        val clues = mapOf("children" to 3,
                "cats" to 7,
                "samoyeds" to 2,
                "pomeranians" to 3,
                "akitas" to 0,
                "vizslas" to 0,
                "goldfish" to 5,
                "trees" to 3,
                "cars" to 2,
                "perfumes" to 1)
        assertTrue(satisfiesPart2Clues(aunt1, clues))
        assertFalse(satisfiesPart2Clues(aunt2, clues))
        assertFalse(satisfiesPart2Clues(aunt3, clues))

    }
}