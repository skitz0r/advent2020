package two

import java.lang.IndexOutOfBoundsException
import java.lang.RuntimeException

object SolverTwo {

    enum class RULE_TYPE {
        COUNT,
        POSITION
    }
    /**
     * Given a bunch of Strings, how many satisfy their own Rule?
     */
    fun solve(lines: Collection<String>, debug: Boolean = false, ruleType: RULE_TYPE = RULE_TYPE.COUNT): Int {
        if (debug) {
            lines.forEach {
                if (it.passesRule(ruleType)) {
                    println("PASS: $it")
                } else {
                    println("FAIL: $it")
                }
            }
        }
        return lines.filter { it.passesRule(ruleType) }.size
    }

    fun String.passesRule(ruleType: RULE_TYPE): Boolean {
        val input = getInput(this)
        val rule = getRule(this)

        when (ruleType) {
            RULE_TYPE.COUNT -> {
                val inputIterator = input.toCharArray().iterator()
                var count = 0
                while (inputIterator.hasNext()) {
                    if (inputIterator.nextChar() == rule.char) {
                        count++
                    }
                }

                return rule.min <= count && count <= rule.max
            }
            RULE_TYPE.POSITION -> {
                return try {
                    val inputArray = input.toCharArray()
                    val minChar = inputArray[rule.min - 1] // stupid idiot rule
                    val maxChar = inputArray[rule.max - 1]
                    (minChar == rule.char) xor (maxChar == rule.char)
                } catch (oob: IndexOutOfBoundsException) {
                    false
                }
            }
        }
    }

    fun getRule(text: String): Rule {
        val ruleText = text.split(":").first()
        val matched = Regex("""(\d+)-(\d+)\s(\S)""").matchEntire(ruleText) ?: throw RuntimeException()
        val min = matched.groups.get(1)!!.value.toInt()
        val max = matched.groups.get(2)!!.value.toInt()
        val char = matched.groups.get(3)!!.value.toCharArray().first()
        return Rule(char, min, max)
    }

    fun getInput(text: String): String = text.split(":").get(1)!!.trim()

}

data class Rule(val char: Char, val min: Int, val max: Int)
