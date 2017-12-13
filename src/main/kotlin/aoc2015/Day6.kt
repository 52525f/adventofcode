package aoc2015

fun main(args: Array<String>) {
    var lights = Array(1000, {Array(1000, { false })})
    println(countLights(lights))
}

fun countLights(lights: Array<Array<Boolean>>): Int {
    return lights.flatten().map { if (it) 1 else 0 }.count()
}