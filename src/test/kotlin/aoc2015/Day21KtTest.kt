package aoc2015

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class Day21KtTest {
    @Test
    fun getsCost() {
        val player = Player(weapons[0], armor[0], listOf(rings[0]))
        assertEquals(46, player.cost)
    }

    @Test
    fun getsExampleWinner() {
        val player = Player(weapons.first { it.damage == 5}, armor.first{ it.defense == 5}, listOf(), hp=8)
        val boss = Boss(12, 7, 2)

        assertTrue(player.winsAgainst(boss))
    }
}