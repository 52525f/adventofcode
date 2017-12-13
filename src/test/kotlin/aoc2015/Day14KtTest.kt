package aoc2015

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class Day14KtTest {
    @Test
    fun parsesInput() {
        val input = "Rudolph can fly 22 km/s for 8 seconds, but then must rest for 165 seconds."
        val reindeer = Reindeer.fromString(input)
        assertEquals("Rudolph", reindeer.name)
        assertEquals(22, reindeer.speed)
        assertEquals(8, reindeer.runTime)
        assertEquals(165, reindeer.restTime)
    }

    @Test
    fun getsDistanceFlownShort() {
        val reindeer = Reindeer("Rudolph", 22, 8, 165)
        assertEquals(110, reindeer.getDistanceFlown(5))
    }

    @Test
    fun solvesExamples() {
        val comet = Reindeer("Comet", 14, 10, 127)
        val dancer = Reindeer("Dancer", 16, 11, 162)
        assertEquals(1120, comet.getDistanceFlown(1000))
        assertEquals(1056, dancer.getDistanceFlown(1000))
    }
}