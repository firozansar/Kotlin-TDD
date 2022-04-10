package info.firozansari.string_calculator

import java.util.regex.Matcher
import java.util.regex.Pattern

/**
 * Class created to work as a number extractor. This class will receive a string with some numbers inside and will
 * extract it in a number collection.
 */
internal class NumberExtractor {
    /**
     * Analyze the string passed as parameter searching numbers and return a list with all of them
     *
     * @param stringToAnalyze can contains different separators and numbers with more than one digit.
     * @return a List<Integer> with the numbers found</Integer>
     */
    fun extract(stringToAnalyze: String): List<Int> {
        var result = listOf<Int>()
        if (!stringToAnalyze.isEmpty()) {
            result = getNumbersUsingRegEx(stringToAnalyze)
        }
        return result
    }

    private fun getNumbersUsingRegEx(string: String): List<Int> {
        val matcher = NUMBER_PATTERN.matcher(string)
        return extractNumbersFromMatcher(matcher)
    }

    private fun extractNumbersFromMatcher(matcher: Matcher): List<Int> {
        val numbers = kotlin.collections.ArrayList<Int>()
        while (matcher.find()) {
            val number = matcher.group().toInt()
            numbers.add(number)
        }
        return numbers
    }

    companion object {
        private const val ONE_DIGIT_OR_MORE_REG_EX = "-?\\d+"
        private val NUMBER_PATTERN = Pattern.compile(ONE_DIGIT_OR_MORE_REG_EX)
    }
}
