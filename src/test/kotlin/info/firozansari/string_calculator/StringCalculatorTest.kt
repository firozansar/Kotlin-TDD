package info.firozansari.string_calculator

import io.mockk.junit5.MockKExtension
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
class StringCalculatorTest {

    private val stringCalculator = StringCalculator()

    @Test
    @Throws(ValidatorException::class)
    fun shouldReturnZeroIfTheArgumentIsEmpty() {
        val result = stringCalculator.add(EMPTY_SET_OF_NUMBERS)

        assertEquals(0, result)
    }

    @Test
    @Throws(ValidatorException::class)
    fun shouldReturnTheSameValueIfTheArgumentContainsOnlyOneNumberWithOneDigit() {
        val result = stringCalculator.add(ONE_NUMBER_ONE_DIGIT_STRING)

        val expectedResult = Integer.parseInt(ONE_NUMBER_ONE_DIGIT_STRING)
        assertEquals(expectedResult, result)
    }

    @Test
    @Throws(ValidatorException::class)
    fun shouldReturnTheSameValueIfTheArgumentContainisOnlyOneNumberWithMoreThanOneDigit() {
        val result = stringCalculator.add(ONE_NUMBER_STRING)

        val expectedResult = Integer.parseInt(ONE_NUMBER_STRING)
        assertEquals(expectedResult, result)
    }

    @Test
    @Throws(ValidatorException::class)
    fun shouldReturnTheSumOfAnUnknownAmountOfNumbers() {
        val result = stringCalculator.add(NUMBERS_STRING_SUM_ELEVEN)

        assertEquals(11, result)
    }

    @Test
    @Throws(ValidatorException::class)
    fun shouldReturnTheSumOfAnUnknownAmountOfNumbersSeparatedWithNewLineCharacters() {
        val result = stringCalculator.add(NUMBERS_STRING_SUM_SIX_SEPARATOR_NEW_LINE)

        assertEquals(6, result)
    }

    @Test
    @Throws(ValidatorException::class)
    fun shouldReturnTheSumOfAnUnknownAmoutOfNumbersSeparatedWithDifferentDelimiters() {
        val result = stringCalculator.add(NUMBERS_STRING_SUM_SEVEN_DIFFERENT_SEPARATORS)

        assertEquals(7, result)
    }

    @Test
    @Throws(NegativeNumbersNotSupportedException::class)
    fun shouldThrowNegativeNumbersNotSupportedException() {
        assertThrows<NegativeNumbersNotSupportedException> {
            stringCalculator.add(NUMBERS_STRING_WITH_NEGATIVE_VALUES)
        }
    }

    @Test
    fun shouldIgnoreNumbersGratherThanOneThousand() {
        val result = stringCalculator.add(NUMBERS_STRING_SUM_TWO_WITH_MORE_THAN_THOUSAND)

        assertEquals(2, result)
    }

    @Test
    fun shouldAllowDifferentDelimitersWithMoreThanOneCharAndMoreThanOneConsecutive() {
        val result = stringCalculator.add(NUMBERS_STRING_SUM_SEVEN_DIFFERENT_SEPARATORS_CONSECUTIVES)

        assertEquals(7, result)
    }

    private companion object {
        private const val EMPTY_SET_OF_NUMBERS = ""
        private const val ONE_NUMBER_ONE_DIGIT_STRING = "1"
        private const val ONE_NUMBER_STRING = "11"
        private const val NUMBERS_STRING_SUM_ELEVEN = "1,2,3,5"
        private const val NUMBERS_STRING_SUM_SIX_SEPARATOR_NEW_LINE = "1\n2,3"
        private const val NUMBERS_STRING_SUM_SEVEN_DIFFERENT_SEPARATORS = "//;\n1;2p4"
        private const val NUMBERS_STRING_WITH_NEGATIVE_VALUES = "//;\n1;2p-4"
        private const val NUMBERS_STRING_SUM_TWO_WITH_MORE_THAN_THOUSAND = "2,1000"
        private const val NUMBERS_STRING_SUM_SEVEN_DIFFERENT_SEPARATORS_CONSECUTIVES = "[*][%]\\n1*2%4"
    }
}
