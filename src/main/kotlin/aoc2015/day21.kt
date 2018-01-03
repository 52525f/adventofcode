package aoc2015

class Item(val name: String, val cost: Int, val damage: Int, val defense: Int) {
    override fun toString(): String {
        return "$name"
    }
}

val weapons = listOf(Item("Dagger", 8, 4, 0),
        Item("Shortsword", 10, 5, 0),
        Item("Warhammer", 25, 6, 0),
        Item("Longsword", 40, 7, 0),
        Item("Greataxe", 74, 8, 0))

val armor = listOf(Item("None", 0, 0, 0),
        Item("Leather", 13, 0, 1),
        Item("Chainmail", 31, 0, 2),
        Item("Splintmail", 53, 0, 3),
        Item("Bandedmail", 75, 0, 4),
        Item("Platemail", 102, 0, 5))

val rings = listOf(Item("None", 0, 0, 0),
        Item("Damage +1", 25, 1, 0),
        Item("Damage +2", 50, 2, 0),
        Item("Damage +3", 100, 3, 0),
        Item("Defense +1", 20, 0, 1),
        Item("Defense +2", 40, 0, 2),
        Item("Defense +3", 80, 0, 3))

interface Character {
    val hp: Int
    val damage: Int
    val defense: Int
}

class Boss(override val hp: Int, override val damage: Int, override val defense: Int) : Character


class Player(private val weapon: Item, private val armor: Item, private val rings: List<Item>,
             override val hp: Int = 100) : Character {
    override val damage: Int
        get() = weapon.damage + rings.map { it.damage }.sum()
    override val defense: Int
        get() = armor.defense + rings.map { it.defense }.sum()

    val cost: Int
        get() = weapon.cost + armor.cost + rings.map { it.cost }.sum()

    fun winsAgainst(opponent: Character): Boolean {
        var playerHp = hp
        var opponentHp = opponent.hp
        while (playerHp > 0 && opponentHp > 0) {
            opponentHp -= attack(this, opponent)
            if (opponentHp <= 0)
                return true
            playerHp -= attack(opponent, this)
        }
        return false
    }

    private fun attack(attacker: Character, defender: Character): Int {
        return if ((attacker.damage - defender.defense) > 1) {
            attacker.damage - defender.defense
        } else {
            1
        }
    }

    override fun toString(): String {
        return "Player(weapon=$weapon, armor=$armor, rings=$rings, cost=$cost)"
    }
}

fun getWinners(boss: Boss): Set<Player> {
    return getPlayerCombinations().filter {
        it.winsAgainst(boss)
    }.toSet()
}

fun getLosers(boss: Boss): Set<Player> {
    return getPlayerCombinations().filter {
        !it.winsAgainst(boss)
    }.toSet()
}


private fun getPlayerCombinations(): Set<Player> {
    val players = mutableSetOf<Player>()
    weapons.forEach { weapon ->
        armor.forEach { armor ->
            rings.forEach { ring1 ->
                rings.forEach { ring2 ->
                    if (ring1.name == "None" || ring1.name != ring2.name) {
                        val player = Player(weapon, armor, listOf(ring1, ring2))
                        players.add(player)
                    }
                }
            }
        }
    }
    return players
}

fun main(args: Array<String>) {
    val boss = Boss(104, 8, 1)
    val cheapest = getWinners(boss).sortedBy { it.cost }.take(1)
    println("part 1")
    println(cheapest)

    val costliest = getLosers(boss).sortedBy { it.cost }.reversed().take(1)
    println("part 2")
    println(costliest)
}
