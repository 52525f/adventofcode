package aoc2015

import java.io.File

fun main(args: Array<String>) {
    val reindeers = mutableListOf<Reindeer>()
    val inputStream = File("src/main/resources/day14.txt").inputStream()
    inputStream.bufferedReader().lines().forEach { reindeers.add(Reindeer.fromString(it))}
    println("Part 1")
    reindeers.forEach { reindeer ->
        println(reindeer.name + " flies " + reindeer.getDistanceFlown(2503))
    }

    println("Part 2")
    solvePart2(reindeers)
}

class Reindeer(val name: String, val speed: Int, val runTime: Int, val restTime: Int) {
    companion object {
        fun fromString(str: String): Reindeer {
            val matches = Regex("(\\w+) .* (\\d+) .* (\\d+) .* (\\d+) .*").find(str)!!.groups
            val name = matches[1]!!.value
            val speed = matches[2]!!.value.toInt()
            val runTime = matches[3]!!.value.toInt()
            val restTime = matches[4]!!.value.toInt()
            return Reindeer(name, speed, runTime, restTime)
        }
    }

    fun getDistanceFlown(time: Int): Int {
        return when {
            time <= 0 -> 0
            time <= runTime -> speed * time
            else -> speed * runTime + getDistanceFlownStartingFromRest( time - runTime)
        }
    }

    private fun getDistanceFlownStartingFromRest(time: Int): Int {
        return if (time <= restTime) {
            0
        } else {
            getDistanceFlown(time - restTime)
        }
    }
}


fun solvePart2(reindeers: List<Reindeer>) {
    val scores: MutableMap<String, Int> = reindeers.map { it.name to 0 }.toMap().toMutableMap()

    for (i in 1..2503) {
        val distances = reindeers.map { it.name to it.getDistanceFlown(i) }.toMap()
        val  max = distances.maxBy { it.value }!!.value
        reindeers.forEach {
            if (distances[it.name] == max)
                scores[it.name] = scores[it.name]!!.plus(1)
        }
    }
    println("scores: " + scores.toList().sortedBy { (_, value) -> value })
}
