package info.firozansari.fizzbuzz

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class FizzBuzzConverterTest {

    private val fizzBuzz = FizzBuzzConverter()

    @Test
    fun outputTheHundredFizzBuzzes() {
        for (i in 1..100) {
            println(fizzBuzz.convert(i))
        }
    }

    @Test
    fun fizzBuzzConvertorLeavesNormalNumbersAlone() {
        assertEquals("1", fizzBuzz.convert(1))
        assertEquals("2", fizzBuzz.convert(2))
    }

    @Test
    fun fizzBuzzConvertorMultiplesOfThree() {
        assertEquals("Fizz", fizzBuzz.convert(3))
    }

    @Test
    fun fizzBuzzConvertorMultiplesOfFive() {
        assertEquals("Buzz", fizzBuzz.convert(5))
    }

    @Test
    fun multiplesOfBothThreeAndFive() {
        assertEquals("FizzBuzz", fizzBuzz.convert(15))
}
