package aoc2015

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class Day19KtTest {
    private val replaceStrings = listOf("H => HO", "H => OH", "O => HH")
    private val replacements = Replacement(replaceStrings)

    @Test
    fun buildsMapOfElements() {
        assertEquals(listOf("HO", "OH"), replacements.forString("H"))
        assertEquals(listOf("HH"), replacements.forString("O"))
    }

    @Test
    fun performsExampleSubstitutions() {
        val molecule = Molecule("HOH", replacements)
        val expected = setOf(Molecule("HOOH", replacements), Molecule("HOHO", replacements),
                Molecule("OHOH", replacements), Molecule("HHHH", replacements))
        assertEquals(expected, molecule.applyReplacements())
    }

    @Test
    fun findsMinimumNumberOfReplacements() {
        val input = listOf("e => H", "e => O", "H => HO", "H => OH", "O => HH")
        val rules = Replacement(input)
        val start = Molecule("e", rules)
        val target = Molecule("HOHOHO", rules)
        assertEquals(6, start.stepsToTransformTo(target))
    }
}
