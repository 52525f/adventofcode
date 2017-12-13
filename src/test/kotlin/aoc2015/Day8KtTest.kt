package aoc2015

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class Day8KtTest {
    @Test
    fun countsTotalSizeOfStrings() {
        assertEquals(16, getTotalCodeChars(listOf("\"I am here\"", "\"foo\"")))
    }

    @Test
    fun countsStringSizeOfStrings() {
        assertEquals(0, getStringChars("\"\""))
        assertEquals(7, getStringChars("\"aaa\\\"aaa\""))
        assertEquals(1, getStringChars("\"\\\\\""))
        assertEquals(1, getStringChars("\"\\x27\""))
    }

    @Test
    fun countsTotalStringChars() {
        assertEquals(9, getTotalStrChars(listOf("", "aaa\\\"aaa", "\\\\", "\\x27")))
    }

}