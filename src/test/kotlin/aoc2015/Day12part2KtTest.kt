package aoc2015

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class Day12part2KtTest {
    @Test
    fun countsArray() {
        val node = readJson("[1,2,3]")
        assertEquals(6, count(node))
    }

    @Test
    fun ignoresRed() {
        var node = readJson("[1,{\"c\":\"red\",\"b\":2},3]")
        assertEquals(4, count(node))

        node = readJson("{\"d\":\"red\",\"e\":[1,2,3,4],\"f\":5}")
        assertEquals(0, count(node))
    }

    @Test
    fun countsArrayWithRed() {
        val node = readJson("[1,\"red\",5]")
        assertEquals(6, count(node))
    }


}