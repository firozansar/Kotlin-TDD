package info.firozansari.stringcalculator

/**
 * Validator created to detect negative numbers. If this validator detects a negative number will throw a
 * NegativeNumbersNotSupportedException.
 */
internal class NegativeNumberSearcher : ForbiddenNumberSearcher {
    @Throws(ValidatorException::class)
    override fun validate(numbers: List<Int>) {
        val negativeNumbers = getNegativeNumbers(numbers)
        if (negativeNumbers.isNotEmpty()) {
            throw NegativeNumbersNotSupportedException(negativeNumbers)
        }
    }

    private fun getNegativeNumbers(numbers: List<Int>): List<Int> {
        val negativeNumbers = mutableListOf<Int>()
        for (num in numbers) {
            if (num < 0) {
                negativeNumbers.add(num)
            }
        }
        return negativeNumbers
    }
}
