package aoc2015

import java.io.File

fun main(args: Array<String>) {
    val lines = File("src/main/resources/day18.txt").readLines()
    var grid = Grid.parseConfiguration(lines)
    for (steps in 1..100) {
        grid = grid.nextState()
    }
    println("Part 1")
    println("Lights on = " + grid.countLights())

    println("Part 2")
    var grid2 = Grid.parseConfigurationForPart2(lines)
    for (steps in 1..100) {
        grid2 = grid2.nextStatePart2()
    }
    println("Lights on = " + grid2.countLights())
}

enum class LightState {
    ON, OFF
}

class Grid(private val size: Int, private val state: Map<Pair<Int, Int>, LightState>) {
    companion object {
        fun parseConfiguration(list: List<String>): Grid {
            val size = list.size
            val map = mutableMapOf<Pair<Int, Int>, LightState>()
            list.forEachIndexed { i, str ->
                str.forEachIndexed { j, c ->
                    map.put(Pair(i, j), if (c == '.') LightState.OFF else LightState.ON)
                }
            }
            return Grid(size, map)
        }

        fun parseConfigurationForPart2(list: List<String>): Grid {
            val grid = parseConfiguration(list)
            val state = grid.state.mapValues {
                val x = it.key.first
                val y = it.key.second
                when {
                    grid.isCorner(x, y) -> LightState.ON
                    else -> it.value
                }
            }
            return Grid(grid.size, state)
        }
    }

    fun nextState(): Grid {
        val newState = state.mapValues {
            val x = it.key.first
            val y = it.key.second
            when {
                it.value == LightState.ON -> nextFromOn(x, y)
                else -> nextFromOff(x, y)
            }
        }
        return Grid(size, newState)
    }

    private fun nextFromOn(x: Int, y: Int): LightState {
        val litNeighbors = countLitNeighbors(x, y)
        return when (litNeighbors) {
            2, 3 -> LightState.ON
            else -> LightState.OFF
        }
    }

    private fun nextFromOff(x: Int, y: Int): LightState {
        val litNeighbors = countLitNeighbors(x, y)
        return when (litNeighbors) {
            3 -> LightState.ON
            else -> LightState.OFF
        }
    }

    fun nextStatePart2(): Grid {
        val newState = state.mapValues {
            val x = it.key.first
            val y = it.key.second
            when {
                isCorner(x, y) -> LightState.ON
                it.value == LightState.ON -> nextFromOn(x, y)
                else -> nextFromOff(x, y)
            }
        }
        return Grid(size, newState)
    }

    private fun isCorner(x: Int, y: Int): Boolean {
        return (x == 0 && y == 0) ||
                (x == 0 && y == size - 1) ||
                (x == size - 1 && y == 0) ||
                (x == size - 1 && y == size - 1)
    }

    fun countLights(): Int {
        return state.map { if (it.value == LightState.ON) 1 else 0 }.sum()
    }

    fun countLitNeighbors(x: Int, y: Int): Int {
        return neighbors(x, y).map { getValue(it.first, it.second) }.sum()
    }

    private fun neighbors(x: Int, y: Int): List<Pair<Int, Int>> {
        return listOf(Pair(x - 1, y - 1), Pair(x - 1, y), Pair(x - 1, y + 1),
                Pair(x, y - 1), Pair(x, y + 1),
                Pair(x + 1, y - 1), Pair(x + 1, y), Pair(x + 1, y + 1))
    }

    private fun getValue(x: Int, y: Int): Int {
        return if (x < 0 || y < 0 || x >= size || y >= size)
            0
        else if (state[Pair(x, y)] == LightState.OFF)
            0
        else
            1
    }

    override fun toString(): String {
        val sb = StringBuilder()
        for (i in 0 until size) {
            for (j in 0 until size) {
                if (state[Pair(i, j)] == LightState.ON)
                    sb.append('#')
                else if (state[Pair(i, j)] == LightState.OFF)
                    sb.append('.')
            }
            sb.append('\n')
        }
        return sb.toString()
    }
}
