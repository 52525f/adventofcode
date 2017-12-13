package aoc2015

val letters = "abcdefghkmnpqrstuvwxyz"
val next = letters.zip(letters.substring(1, letters.length) + "a").toMap()

fun main(args: Array<String>) {

    val pass = "azz"
    var stillIncrementing = true
    val sb = StringBuilder()
    for (c in pass.reversed()) {
        if(stillIncrementing) {
            sb.append(next[c])
            if (c != 'z') {
                stillIncrementing = false
            }
        } else {
            sb.append(c)
        }
    }
    val newpass = sb.toString().reversed()
    println(newpass)
    println(incrementString("azz"))
}

fun incrementString(s: String): String {
    return incrementReversed(s.reversed()).reversed()
}

private fun incrementReversed(s: String): String {

    if (s.isEmpty())
        return ""

    val head = s.first()
    return if (head != 'z') {
        next[head].toString() + s.substring(1)
    } else {
        next[head].toString() + incrementString(s.substring(1))
    }
}