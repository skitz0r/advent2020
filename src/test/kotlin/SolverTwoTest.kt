import org.junit.jupiter.api.Test
import two.Rule
import two.SolverTwo

class SolverTwoTest {

    val inputStream = SolverOneTest::class.java.getResource("/input2").openStream()
    val inputs = inputStream.reader().readLines()

    @Test
    fun partOne() {
        println("Part one: ${SolverTwo.solve(inputs, ruleType = SolverTwo.RULE_TYPE.COUNT)}")
    }

    @Test
    fun partTwo() {
        println("Part two: ${SolverTwo.solve(inputs, debug = true, ruleType = SolverTwo.RULE_TYPE.POSITION)}")
    }

    @Test
    fun testRuleParser() {
        val input1 = "1-3 a: abcde"
        val input2 = "1-3 b: cdefg"
        val input3 = "2-9 c: ccccccccc"

        assert(SolverTwo.getRule(input1) == Rule(char = 'a', min = 1, max = 3))
        assert(SolverTwo.getInput(input1) == "abcde")

        assert(SolverTwo.getRule(input2) == Rule(char = 'b', min = 1, max = 3))
        assert(SolverTwo.getInput(input2) == "cdefg")

        assert(SolverTwo.getRule(input3) == Rule(char = 'c', min = 2, max = 9))
        assert(SolverTwo.getInput(input3) == "ccccccccc")
    }
}