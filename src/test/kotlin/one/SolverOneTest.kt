package one

import org.junit.jupiter.api.Test

class SolverOneTest {

    val inputStream = SolverOneTest::class.java.getResource("/one/input").openStream()
    val inputs = inputStream.reader().readLines().map { it.toInt() }

    @Test
    fun partOne() {
        val solution = SolverOne.solve(inputs, SolverOne.SIZE.TWO)
        println("Part one: $solution")
    }

    @Test
    fun partTwo() {
        val solution = SolverOne.solve(inputs, SolverOne.SIZE.THREE)
        println("Part two: $solution")
    }
}