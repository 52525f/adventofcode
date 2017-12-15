package aoc2015

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class Day15KtTest {
    @Test
    fun getsIngredient() {
        val s = "Sprinkles: capacity 5, durability -1, flavor 0, texture 0, calories 5"
        val ingredient = Ingredient.fromString(s)
        assertEquals("Sprinkles", ingredient.name)
        assertEquals(5, ingredient.capacity)
        assertEquals(-1, ingredient.durability)
        assertEquals(0, ingredient.flavor)
        assertEquals(0, ingredient.texture)
        assertEquals(5, ingredient.calories)
    }

    @Test
    fun generatesEmptyCombinations() {
        val empty: List<Array<Int>> = listOf()
        val list = combinations(3, 0)
        assertEquals(empty, list)
    }

    @Test
    fun generatesCombinationsOfOneItem() {
        val list = combinations(3, 1)
        assertEquals(1, list.size)
        assertEquals(3, list[0][0])
    }

    @Test
    fun generatesCombinationsOfTwoItems() {
        // [0, 3] [1, 2] [2, 1] [3, 0]
        val list = combinations(3, 2)
        assertEquals(4, list.size)
        assertTrue(list.contains(listOf(0, 3)))
        assertTrue(list.contains(listOf(1, 2)))
        assertTrue(list.contains(listOf(2, 1)))
        assertTrue(list.contains(listOf(3, 0)))
    }


    @Test
    fun getsScore() {
        val ingredients = listOf(Ingredient.fromString("Butterscotch: capacity -1, durability -2, flavor 6, texture 3, calories 8"),
                Ingredient.fromString("Cinnamon: capacity 2, durability 3, flavor -2, texture -1, calories 3"))
        val mix = listOf(44, 56)
        assertEquals(62842880, getScore(ingredients, mix))
    }

    @Test
    fun getsBestMix() {
        val ingredients = listOf(Ingredient.fromString("Butterscotch: capacity -1, durability -2, flavor 6, texture 3, calories 8"),
                Ingredient.fromString("Cinnamon: capacity 2, durability 3, flavor -2, texture -1, calories 3"))
        val mixture = getBestRecipe(ingredients)
        assertEquals(listOf(44, 56), mixture.first)
        assertEquals(62842880, mixture.second)
    }

    @Test
    fun getsBestRecipeWith500Calories() {
        val ingredients = listOf(Ingredient.fromString("Butterscotch: capacity -1, durability -2, flavor 6, texture 3, calories 8"),
                Ingredient.fromString("Cinnamon: capacity 2, durability 3, flavor -2, texture -1, calories 3"))
        val mixture = getBestRecipeWith500Calories(ingredients)
        assertEquals(listOf(40, 60), mixture.first)
        assertEquals(57600000, mixture.second)

    }
}