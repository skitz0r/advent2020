package two

object SolverThree {

    fun solve(input: Collection<String>, movementPatterns: Collection<MovementPattern>, debug: Boolean): Int {
        val collisions = movementPatterns.map { solve(input, it, debug) }.toList()
        if (debug) { collisions.forEach { println(it) } }
        return collisions.fold(1) { product, element -> product * element }
    }

    /**
     * ..##.........##..
     * Given a bunch of these, how many #'s do you hit
     * following a particular pattern
     *
     * we can arbitrarily decide this based on the row of the line and the guys movement pattern
     */
    fun solve(input: Collection<String>, movementPattern: MovementPattern, debug: Boolean): Int {
        return input.mapIndexed { index: Int, treePattern: String -> Line(index, treePattern, movementPattern) }
            .filter { it.actuallyVisited }
            .apply { if (debug) { println(this) } }
            .filter { it.didDudeHitTreeOnLine }
            .count()
    }
}

/**
 * order is 0 based for the order it appears
 */
data class Line(val order: Int, val treePattern: String, val movementPattern: MovementPattern) {
    // if we jump 2 at a time, we visit 2,4,6,8 and filter 1,3,5,7,etc
    val actuallyVisited = order % movementPattern.y == 0

    // if we move 2 times, then on line 2 we've moved once (2/2), line 4 twice (4/2), etc.
    val timesMoved: Int = order / movementPattern.y

    // dudes position is going to be amount moved horizontally % pattern length
    // ex if he moves to the right 5 times and the pattern is 7 long, on row 3 he will be in position 16 horizontally
    // 16 % 7 = 2, hes at pos 2 on row 3
    // 0 X...... ....... ....... (0 * 5) + 1 % 7 = 1
    // 1 .....X. ....... ....... (1 * 5) + 1 % 7 = 6
    // 2 ....... ...X... ....... (2 * 5) + 1 % 7 = 4
    // 3 ....... ....... .X..... (3 * 5) + 1 % 7 = 2
    val dudeOffSetAtLine = if (timesMoved == 0) 1 else ((timesMoved * movementPattern.x) % treePattern.length) + 1

    // position is base 1, so sub 1 here
    val didDudeHitTreeOnLine = treePattern.toCharArray()[dudeOffSetAtLine - 1] == ('#')

    override fun toString(): String = "$treePattern OFFSET: $dudeOffSetAtLine $didDudeHitTreeOnLine\n"
}

class MovementPattern(val x: Int, val y: Int)