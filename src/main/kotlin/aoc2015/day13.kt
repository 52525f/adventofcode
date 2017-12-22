package aoc2015

import java.io.File
import java.io.InputStream

class Day13 {

    fun parse(strs: List<String>): Map<String, Map<String, Int>> {
        val re = Regex("([\\w]+) would ([\\w]+) ([\\d]+).* ([\\w]+)\\.")
        val map = mutableMapOf<String, MutableMap<String, Int>>()
        strs.forEach {
            val matches = re.find(it)!!.groups
            val name = matches[1]!!.value
            val value = if (matches[2]!!.value == "lose") -matches[3]!!.value.toInt() else matches[3]!!.value.toInt()
            val neighbor = matches[4]!!.value
            val likes = map.getOrDefault(name, mutableMapOf())
            likes.put(neighbor, value)
            map.put(name, likes)
        }
        return map
    }

    fun computeHappiness(list: List<String>, preferences: Map<String, Map<String, Int>>): Int {
       return neighbors(list).map {
           preferences[it.first]!!.getOrDefault(it.second, 0) +
           preferences[it.second]!!.getOrDefault(it.first, 0)
       }.sum()
    }

    fun neighbors(names: List<String>): List<Pair<String, String>> {
        return names.zip(names.subList(1, names.size)) + Pair(names.last(), names.first())
    }

    fun generateArrangements(names: List<String>): Set<List<String>> {
        return when {
            names.isEmpty() -> emptySet()
            names.size == 1 -> setOf(names)
            else -> {
                val current: MutableSet<List<String>> = mutableSetOf()
                names.forEach { head ->
                    val rest = names - head
                    generateArrangements(rest).forEach {
                        current.add(listOf(head) + it)
                    }
                }
                current
            }
        }
    }

    fun findBestArrangement(likes: Map<String, Map<String, Int>>): List<String> {
        return generateArrangements(likes.keys.toList())
                .map { Pair(it, computeHappiness(it, likes)) }
                .maxBy { it.second }!!
                .first
    }

    fun addMeToPreferencesMap(likes: Map<String, Map<String, Int>>): Map<String, Map<String, Int>> {
        val map = likes.toMutableMap()
        val names = map.keys
        val myLikes = mutableMapOf<String, Int>()
        names.forEach { myLikes.put(it, 0) }
        map.put("Me", myLikes)
        return map
    }
}

fun main(args: Array<String>) {
    val list = mutableListOf<String>()
    val inputStream: InputStream = File("src/main/resources/day13.txt").inputStream()
    inputStream.bufferedReader().useLines { lines -> lines.forEach { list.add(it) } }
    val day13 = Day13()
    val prefs = day13.parse(list)
    val arrangement = day13.findBestArrangement(prefs)
    println("Part 1")
    println(arrangement)
    println(day13.computeHappiness(arrangement, prefs))

    val part2Prefs= day13.addMeToPreferencesMap(prefs)
    val part2Arrangement = day13.findBestArrangement(part2Prefs)
    println("Part 2")
    println(part2Arrangement)
    println(day13.computeHappiness(part2Arrangement, part2Prefs))
}