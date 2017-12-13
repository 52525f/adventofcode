package aoc2015

import java.io.File

fun main(args: Array<String>) {
    val list = mutableListOf<String>()
    val inputStream = File("src/main/resources/day14.txt").inputStream()
    inputStream.bufferedReader().lines().forEach { list.add(it)}
    list.forEach {
        val reindeer = Reindeer.fromString(it)
        println(reindeer.name + " flies " + reindeer.getDistanceFlown(2503))
    }
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

