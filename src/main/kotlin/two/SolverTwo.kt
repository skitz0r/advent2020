package two

import java.lang.RuntimeException

object SolverTwo {

    /**
     * Given a bunch of Strings, how many satisfy their own Rule?
     */
    fun solve(lines: Collection<String>): Int {
        return lines.filter { it.passesRule() }.size
    }

    fun String.passesRule(): Boolean {
        val input = getInput(this)
        val rule = getRule(this)

        val inputIterator = input.toCharArray().iterator()
        var count = 0
        while (inputIterator.hasNext()) {
            if (inputIterator.nextChar() == rule.char) {
                count++
            }
        }

        return rule.min >= count &&
                rule.max >= count
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
