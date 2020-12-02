package one

import java.lang.RuntimeException

object SolverOne {

    enum class SIZE {
        TWO,
        THREE
    }

    /**
     * Given a collection of numbers, find the pair which sums to 2020, return their product.
     *
     * Blows up if there is more than one pair.
     */
    fun solve(inputs: Collection<Int>, size: SIZE): Int {
        return when (size) {
            SIZE.TWO -> {
                val pair = findPairs(inputs) ?: throw RuntimeException()
                return pair.first * pair.second
            }
            SIZE.THREE -> {
                val triple = findTriplet(inputs) ?: throw RuntimeException()
                return triple.first * triple.second * triple.third
            }
        }
    }

    /**
     * There are a number of ways to do this that I can think of:
     *   1) brute force
     *   2) identify last digit 0 sum
     *   3) eliminate any pairings of numbers 3 digits or less
     *   4) subtract value off 2020, see if remainder is in list
     *
     *   lets try 4, this is more or less a brute force, but its simple to write
     *
     *   note: we should obviously collapse the input into a set
     */
    fun findPairs(inputs: Collection<Int>): Pair<Int, Int>? {
        inputs.forEach {
            val difference = 2020 - it
            if (inputs.contains(difference) && it != difference) {
                return Pair(it, difference)
            }
        }
        return null
    }

    /**
     * Brute forcing this now seems more more costly...
     *
     * We can reduce this problem to pairs, but its not really any more efficient than brute forcing.
     * am I missing something?
     *
     * other thoughts:
     *   can eliminate anything from pairs
     *   all 3 numbers cant be > 1000
     *   last digit must sum to 0 again? cant really find this without adding all the nums anyway?
     *
     * Identify A,B,C s.t A+B+C = 2020
     *
     */
    fun findTriplet(inputs: Collection<Int>, target: Int = 2020): Triple<Int, Int, Int>? {
        // (A+B) -> (A,B)
        return inputs.map { sumsExceptSelf(it, inputs) }
            .mapNotNull { findTriple(inputs, it, target) }
            .first()
    }

    // For base A and args B, collect (A+B) -> (A,B)
    private fun sumsExceptSelf(base: Int, args: Collection<Int>): Map<Int, Pair<Int, Int>> {
        val map = mutableMapOf<Int, Pair<Int, Int>>()
        args.filter { it != base }.map { it + base }.associateWithTo(map) { sum -> Pair(base, sum - base) }
        return map
    }

    // Does target - key exist in the inputs? If so, return that entrys value
    private fun findTriple(
        inputs: Collection<Int>,
        map: Map<Int, Pair<Int, Int>>,
        target: Int = 2020
    ): Triple<Int, Int, Int>? {
        map.entries.forEach {
            val dif = target - it.key
            if (inputs.contains(dif) && setOf(dif, it.value.first, it.value.second).size == 3) {
                return Triple(dif, it.value.first, it.value.second)
            }
        }
        return null
    }
}