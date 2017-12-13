package aoc2015

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class Day13KtTest {
    @Test
    fun getsNeighbors() {
        val pairs = Day13().neighbors(listOf("a", "b", "c"))
        assertEquals(listOf(Pair("a", "b"), Pair("b", "c"), Pair("c", "a")), pairs)
    }

    @Test
    fun generatesArrangements() {
        val names = listOf("a", "b", "c")
        val arrangements = Day13().generateArrangements(names)
        assertEquals(setOf(listOf("a", "b", "c"), listOf("a", "c", "b"),
                listOf("b", "a", "c"), listOf("b", "c", "a"),
                listOf("c", "a", "b"), listOf("c", "b", "a")), arrangements)
    }

    @Test
    fun parsesInput() {
        val str = listOf("Alice would gain 54 happiness units by sitting next to Bob.",
                "Alice would lose 79 happiness units by sitting next to Carol.")
        val map = Day13().parse(str)
        assertEquals(54, map["Alice"]!!["Bob"])
        assertEquals(-79, map["Alice"]!!["Carol"])
    }

    @Test
    fun computesHappiness() {
        val input = listOf(
                "Alice would gain 54 happiness units by sitting next to Bob.",
                "Alice would lose 79 happiness units by sitting next to Carol.",
                "Alice would lose 2 happiness units by sitting next to David.",
                "Bob would gain 83 happiness units by sitting next to Alice.",
                "Bob would lose 7 happiness units by sitting next to Carol.",
                "Bob would lose 63 happiness units by sitting next to David.",
                "Carol would lose 62 happiness units by sitting next to Alice.",
                "Carol would gain 60 happiness units by sitting next to Bob.",
                "Carol would gain 55 happiness units by sitting next to David.",
                "David would gain 46 happiness units by sitting next to Alice.",
                "David would lose 7 happiness units by sitting next to Bob.",
                "David would gain 41 happiness units by sitting next to Carol.")
        val day13 = Day13()
        val prefs = day13.parse(input)
        val seating = listOf("Alice", "Bob", "Carol", "David")
        assertEquals(330, day13.computeHappiness(seating, prefs))
    }

    @Test
    fun findsBestArrangement() {
        val input = listOf(
                "Alice would gain 54 happiness units by sitting next to Bob.",
                "Alice would lose 79 happiness units by sitting next to Carol.",
                "Alice would lose 2 happiness units by sitting next to David.",
                "Bob would gain 83 happiness units by sitting next to Alice.",
                "Bob would lose 7 happiness units by sitting next to Carol.",
                "Bob would lose 63 happiness units by sitting next to David.",
                "Carol would lose 62 happiness units by sitting next to Alice.",
                "Carol would gain 60 happiness units by sitting next to Bob.",
                "Carol would gain 55 happiness units by sitting next to David.",
                "David would gain 46 happiness units by sitting next to Alice.",
                "David would lose 7 happiness units by sitting next to Bob.",
                "David would gain 41 happiness units by sitting next to Carol.")
        val day13 = Day13()
        val prefs = day13.parse(input)
        val arrangement = day13.findBestArrangement(prefs)
        assertEquals(330, day13.computeHappiness(arrangement, prefs))
    }
}