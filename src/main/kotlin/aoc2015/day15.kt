package aoc2015

val input = listOf("Sprinkles: capacity 5, durability -1, flavor 0, texture 0, calories 5",
        "PeanutButter: capacity -1, durability 3, flavor 0, texture 0, calories 1",
        "Frosting: capacity 0, durability -1, flavor 4, texture 0, calories 6",
        "Sugar: capacity -1, durability 0, flavor 0, texture 2, calories 8")

fun main(args: Array<String>) {
    val ingredients = input.map { Ingredient.fromString(it) }
    val result = getBestRecipe(ingredients)
    println("Part 1")
    println("Best recipe: " + result.first)
    println("Score: " + result.second)

    val part2 = getBestRecipeWith500Calories(ingredients)
    println("Part 2")
    println("Best recipe: " + part2.first)
    println("Score: " + part2.second)
}

class Ingredient(val name: String, val capacity: Int, val durability: Int, val flavor: Int, val texture: Int,
                 val calories: Int) {
    companion object {
        fun fromString(str: String): Ingredient {
            val re = Regex("([\\w]+): capacity ([-\\d]+), durability ([-\\d]+), flavor ([-\\d]+), texture ([-\\d]+), calories ([-\\d]+)")
            val matches = re.find(str)!!.groups
            val name = matches[1]!!.value
            val capacity = matches[2]!!.value.toInt()
            val durability = matches[3]!!.value.toInt()
            val flavor = matches[4]!!.value.toInt()
            val texture = matches[5]!!.value.toInt()
            val calories = matches[6]!!.value.toInt()
            return Ingredient(name, capacity, durability, flavor, texture, calories)
        }
    }
}


/*
combinations(n, 0) = []
combinations(n, 1) = [n]
combinations(n, k) = [0]::combinations(n-0, k-1) + [1]::combinations(n-1, k-1) + ... + k::combinations(0, k-1)
 */
fun combinations(total: Int, items: Int): List<List<Int>> {
    return when (items) {
        0 -> listOf()
        1 -> listOf(listOf(total))
        else -> IntRange(0, total).flatMap { value ->
            combinations(total - value, items - 1).map { listOf(value) + it }
        }
    }
}


fun getScore(ingredients: List<Ingredient>, mix: List<Int>): Long {
    val quantities = ingredients.map { it.name }.zip(mix).toMap()
    val capacity = ingredients.map { it.capacity * quantities[it.name]!! }.sum().toLong()
    val durability = ingredients.map { it.durability * quantities[it.name]!! }.sum().toLong()
    val flavor = ingredients.map { it.flavor * quantities[it.name]!! }.sum().toLong()
    val texture = ingredients.map { it.texture * quantities[it.name]!! }.sum().toLong()
    return if (capacity < 0 || durability < 0 || flavor < 0 || texture < 0) {
        0
    } else {
        capacity * durability * flavor * texture
    }
}

fun getBestRecipe(ingredients: List<Ingredient>): Pair<List<Int>, Long> {
    return combinations(100, ingredients.size).map {
        Pair(it, getScore(ingredients, it))
    }.maxBy { it.second }!!
}

fun getBestRecipeWith500Calories(ingredients: List<Ingredient>): Pair<List<Int>, Long> {
    return combinations(100, ingredients.size)
            .filter { has500Calories(ingredients, it) }
            .map {
                Pair(it, getScore(ingredients, it))
            }.maxBy { it.second }!!
}


private fun has500Calories(ingredients: List<Ingredient>, mix: List<Int>): Boolean {
    val quantities = ingredients.map { it.name }.zip(mix).toMap()
    val calories = ingredients.map { it.calories * quantities[it.name]!! }.sum()
    return calories == 500
}
