package aoc2015

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class Day10KtTest {
    @Test
    fun processesExampleSequences() {
        assertEquals("11", lookSay("1"))
        assertEquals("21", lookSay("11"))
        assertEquals("1211", lookSay("21"))
        assertEquals("111221", lookSay("1211"))
        assertEquals("312211", lookSay("111221"))
    }
}