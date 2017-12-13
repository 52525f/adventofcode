package aoc2015

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import java.io.File
import java.io.InputStream

fun main(args: Array<String>) {
    val sb = StringBuilder()
    val inputStream: InputStream = File("src/main/resources/day12.json").inputStream()
    inputStream.bufferedReader().useLines { lines -> lines.forEach { sb.append(it) } }
    val rootNode = readJson(sb.toString())
    println(count(rootNode))
}

fun readJson(s: String): JsonNode {
    return ObjectMapper().readTree(s)
}

fun count(node: JsonNode): Int {
    return when {
        node.isArray -> node.elements().asSequence().map { count(it) }.sum()
        node.isInt -> node.asInt()
        node.isObject && !hasRed(node) -> node.fields().asSequence().map { count(it.value) }.sum()
        else -> 0
    }
}

private fun hasRed(node: JsonNode): Boolean {
    return node.fields().asSequence().any { it.value.asText() == "red" }
}
