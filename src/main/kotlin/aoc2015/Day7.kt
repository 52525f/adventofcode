package aoc2015

import java.io.File
import java.io.InputStream

fun parseInstructions(instructions: MutableList<String>): Map<String, Int> {
    val wireMap = HashMap<String, Int>()
    while (!instructions.isEmpty()) {
        val iterator = instructions.iterator()
        while (iterator.hasNext()) {
            val current = iterator.next()
            val args = current.split(" ")
            when {
                current.contains("NOT") -> {
                    if (wireMap.containsKey(args[1])) {
                        wireMap.put(args[3], wireMap[args[1]]!!.inv())
                        iterator.remove()
                    }
                }
                current.contains("AND") -> {
                    if (args[0].toIntOrNull() != null && wireMap.containsKey(args[2])) {
                        wireMap.put(args[4], args[0].toInt() and wireMap[args[2]]!!)
                        iterator.remove()
                    } else if (wireMap.containsKey(args[0]) && wireMap.containsKey(args[2])) {
                        wireMap.put(args[4], wireMap[args[0]]!! and wireMap[args[2]]!!)
                        iterator.remove()
                    }
                }
                current.contains("OR") -> {
                    if (wireMap.containsKey(args[0]) && wireMap.containsKey(args[2])) {
                        wireMap.put(args[4], wireMap[args[0]]!! or wireMap[args[2]]!!)
                        iterator.remove()
                    }
                }
                current.contains("LSHIFT") -> {
                    if (wireMap.containsKey(args[0])) {
                        wireMap.put(args[4], wireMap[args[0]]!!.toInt() shl args[2].toInt())
                        iterator.remove()
                    }
                }
                current.contains("RSHIFT") -> {
                    if (wireMap.containsKey(args[0])) {
                        wireMap.put(args[4], wireMap[args[0]]!!.toInt() shr args[2].toInt())
                        iterator.remove()
                    }
                }
                else -> {
                    if (args[0].toIntOrNull() != null) {
                        wireMap.put(args[2], args[0].toInt())
                        iterator.remove()
                    } else if (wireMap.containsKey(args[0])) {
                        wireMap.put(args[2], wireMap[args[0]]!!)
                        iterator.remove()
                    }
                }
            }
        }
    }
    return wireMap.mapValues { it.value and 0xffff }
}

fun main(args: Array<String>) {
//    val instructions = listOf("123 -> x",
//            "456 -> y",
//            "x AND y -> d",
//            "x OR y -> e",
//            "x LSHIFT 2 -> f",
//            "y RSHIFT 2 -> g",
//            "NOT x -> h",
//            "NOT y -> i")
    val instructions = mutableListOf<String>()
    val inputStream: InputStream = File("src/main/resources/day72.txt").inputStream()
    inputStream.bufferedReader().useLines { lines -> lines.forEach { instructions.add(it) } }
    println(instructions.size)
    val wireMap = parseInstructions(instructions)
    wireMap.forEach {
        println(it.key + " -> " + it.value)
    }

}