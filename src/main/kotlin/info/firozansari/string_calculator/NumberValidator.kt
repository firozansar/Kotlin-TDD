package info.firozansari.string_calculator

import java.util.*

/**
 * Class created to evaluate witch are valid numbers inside a List<Integer>. This implementation it's based on a
 * ValidationRule collection that will be executed each time a List<Integer> be validated.
 */
internal class NumberValidator(private val rules: Collection<ValidationRule>) {
    /**
     * For each element inside the numbers parameter this method will evaluate if it's valid to add it or not to the
     * result collection.
     *
     * @param numbers to analyze.
     * @return a List<Integer> without the invalid numbers
     */
    fun removeNotValidNumbers(numbers: List<Int>): List<Int> {
        val result: MutableList<Int> = LinkedList()
        for (num in numbers) {
            if (isValid(num)) {
                result.add(num)
            }
        }
        return result
    }

    private fun isValid(num: Int): Boolean {
        var res = true
        for (rule in rules) {
            if (!rule.isValid(num)) {
                res = false
                break
            }
        }
        return res
    }

    /**
     * Little interface created to represent a validation rule.
     */
    internal interface ValidationRule {
        fun isValid(number: Int?): Boolean
    }
}
