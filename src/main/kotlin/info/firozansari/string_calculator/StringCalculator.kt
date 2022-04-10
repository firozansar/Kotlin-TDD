package info.firozansari.string_calculator

import java.util.*

/**
 * Requirements:
 *
 * 1. Create a simple String calculator with a method int Add(string numbers)
 * The method can take 0, 1 or 2 numbers, and will return their sum (for an empty string it will return 0) for example “” or “1” or “1,2”.
 * Start with the simplest test case of an empty string and move to 1 and two numbers.
 * Remember to solve things as simply as possible so that you force yourself to write tests you did not think about.
 * Remember to refactor after each passing test.
 *
 * 2. Allow the Add method to handle an unknown amount of numbers.
 *
 * 3. Allow the Add method to handle new lines between numbers (instead of commas). The following input is ok: “1\n2,3” (will equal 6)
 * but this input is NOT ok: “1,\n” (not need to prove it - just clarifying).
 *
 * 4. Support different delimiters. To change a delimiter, the beginning of the string will contain a separate line that
 * looks like this: “//[delimiter]\n[numbers…]” for example “//;\n1;2” should return three where the default delimiter
 * is ‘;’. the first line is optional. all existing scenarios should still be supported.
 *
 * 5. Calling Add with a negative number will throw an exception “negatives not allowed” - and the negative that was
 * passed. if there are multiple negatives, show all of them in the exception message.
 *
 * 6. Numbers bigger than 1000 should be ignored, so adding 2 + 1001 = 2
 *
 * 7. Delimiters can be of any length with the following format: “//[delimiter]\n” for example: “//[]\n12***3” should return 6
 *
 * 8. Allow multiple delimiters like this: “//[delim1][delim2]\n” for example “//[][%]\n12%3” should return 6.
 *
 * 9. Make sure you can also handle multiple delimiters with length longer than one char
 *
 * For more information check the docs folder for "TDD Example Walkthrough"
 */
class StringCalculator {
    private lateinit var numberExtractor: NumberExtractor
    private lateinit var negativeNumberSearcher: ForbiddenNumberSearcher
    private lateinit var numberValidator: NumberValidator

    init {
        initializeNumberExtractor()
        initializeNegativeNumberValidator()
        initializeNumbersValidator()
    }

    /**
     * Parse a string and sum all the numeric values removing the non-numeric characters.
     *
     * @param numbers to analyze and sum.
     * @return the sum value with some restrictions described in the project documentation.
     */
    @Throws(ValidatorException::class)
    fun add(numbers: String): Int {
        var numbersList = extractNumbers(numbers)
        checkIfThereAreNegativeNumbers(numbersList)
        numbersList = validateNumbers(numbersList)
        return sumNumbers(numbersList)
    }

    private fun initializeNumbersValidator() {
        val rule: NumberValidator.ValidationRule = LessThanValidationRule(VALIDATION_TOP_NUMBER)
        val rules: MutableCollection<NumberValidator.ValidationRule> = LinkedList<NumberValidator.ValidationRule>()
        rules.add(rule)
        numberValidator = NumberValidator(rules)
    }

    private fun initializeNegativeNumberValidator() {
        negativeNumberSearcher = NegativeNumberSearcher()
    }

    private fun initializeNumberExtractor() {
        numberExtractor = NumberExtractor()
    }

    private fun extractNumbers(numbers: String): List<Int> {
        return numberExtractor.extract(numbers)
    }

    @Throws(ValidatorException::class)
    private fun checkIfThereAreNegativeNumbers(numbersList: List<Int>) {
        negativeNumberSearcher.validate(numbersList)
    }

    private fun validateNumbers(numbersList: List<Int>): List<Int> {
        return numberValidator.removeNotValidNumbers(numbersList)
    }

    private fun sumNumbers(numberList: List<Int>): Int {
        var sum = 0
        for (num in numberList) {
            sum += num
        }
        return sum
    }

    companion object {
        private const val VALIDATION_TOP_NUMBER = 1000
    }
}
