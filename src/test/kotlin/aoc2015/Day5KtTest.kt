package aoc2015

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class Day5KtTest {
    @Test
    fun isNicePart1() {
        assertTrue(partOneIsNice("ugknbfddgicrmopn"))
        assertTrue(partOneIsNice("aaa"))
        assertFalse(partOneIsNice("jchzalrnumimnmhp"))
        assertFalse(partOneIsNice("haegwjzuvuyypxyu"))
        assertFalse(partOneIsNice("dvszwmarrgswjxmb"))
    }

    @Test
    fun isNicePart2() {
        assertTrue(partTwoIsNice("qjhvhtzxzqqjkmp"))
        assertTrue(partTwoIsNice("xxyxx"))
        assertFalse(partTwoIsNice("uurcxstgmygtbstg"))
        assertFalse(partTwoIsNice("ieodomkazucvgmuy"))
    }
}