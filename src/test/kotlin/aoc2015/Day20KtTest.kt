package aoc2015

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class Day20KtTest {
    @Test
    fun getsFactors() {
        assertEquals(setOf(1), getFactors(1))
        assertEquals(setOf(1, 2), getFactors(2))
        assertEquals(setOf(1, 3), getFactors(3))
        assertEquals(setOf(1, 2, 4), getFactors(4))
        assertEquals(setOf(1, 3, 5, 15), getFactors(15))
        assertEquals(setOf(1, 2, 3, 5, 6, 9, 10, 15, 18, 30, 45, 90), getFactors(90))
    }
}