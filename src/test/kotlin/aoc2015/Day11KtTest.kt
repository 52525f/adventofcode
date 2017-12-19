package aoc2015

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class Day11KtTest {

    @Test
    fun incrementsPassword() {
        assertEquals("xy", incrementPassword("xx"))
        assertEquals("xz", incrementPassword("xy"))
        assertEquals("ya", incrementPassword("xz"))
        assertEquals("zaaaa", incrementPassword("yzzzz"))
    }

    @Test
    fun checksForTwoPairs() {
        assertTrue(hasTwoPairs("aabb"))
        assertTrue(hasTwoPairs("baacc"))
        assertFalse(hasTwoPairs("abc"))
        assertFalse(hasTwoPairs("abcc"))
    }

    @Test
    fun checksForStraights() {
        assertTrue(hasStraight("abc"))
        assertFalse(hasStraight("abd"))
        assertTrue(hasStraight("abxyz"))
    }


    @Test
    fun findsNextPassword() {
        assertEquals("abcdffaa", getNextPassword("abcdefgh"))
        assertEquals("ghjaabcc", getNextPassword("ghijklmn"))

    }
}