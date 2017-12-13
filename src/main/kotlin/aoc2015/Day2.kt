package aoc2015

fun main(args: Array<String>): Unit {
    var totalWrappingPaper = 0
    var totalRibbon = 0
    while(true) {
        val line = readLine() ?: break
        val dimensions = line.split("x")
        if (dimensions.size != 3)
            break
        val l = dimensions[0].toInt()
        val w = dimensions[1].toInt()
        val h = dimensions[2].toInt()

        totalWrappingPaper += surfaceArea(l, w, h) + areaOfSmallestSide(l, w, h)
        totalRibbon += smallestPerimeter(l, w, h) + volume(l, w, h)
    }
    println("Wrapping paper = " + totalWrappingPaper)
    println("Ribbon = " + totalRibbon)
}

fun surfaceArea(l: Int, w: Int, h: Int): Int {
    return 2*l*w + 2*w*h + 2*h*l
}

fun areaOfSmallestSide(l: Int, w: Int, h: Int): Int {
    return minOf(l*w, w*h, h*l)
}

fun smallestPerimeter(l: Int, w: Int, h: Int): Int {
    return minOf(perimeter(l, w), perimeter(w, h), perimeter(h, l))
}

fun perimeter(x: Int, y: Int): Int {
    return 2*x + 2*y
}

fun volume(l: Int, w: Int, h: Int): Int {
    return  l * w * h
}