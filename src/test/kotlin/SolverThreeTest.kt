import org.junit.jupiter.api.Test
import two.MovementPattern
import two.SolverThree

class SolverThreeTest {
    val input = getInputForDay(3)

    @Test
    fun partOne() {
        println(SolverThree.solve(input, MovementPattern(x=3, y=1), true))
    }

    @Test
    fun partTwo() {
        println(SolverThree.solve(
            input,
            listOf(
                MovementPattern(x=1, y=1),
                MovementPattern(x=3, y=1),
                MovementPattern(x=5, y=1),
                MovementPattern(x=7, y=1),
                MovementPattern(x=1, y=2),
            ),
            true)
        )
    }
}