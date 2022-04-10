package info.firozansari.fizzbuzz

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class FizzBuzzProcessorTest {

    private val fb = FizzBuzzProcessor

    @Test
    fun testNormalNumbers() {
        assertEquals("1", fb.convert(1))
        assertEquals("2", fb.convert(2))
    }

    @Test
    fun testNumberThree() {
        assertEquals("Fizz", fb.convert(3))
    }

    @Test
    fun testNumberFive() {
        assertEquals("Buzz", fb.convert(5))
    }

    @Test
    fun testNumberThreeAndFive() {
        assertEquals("Buzz", fb.convert(5))
    }
}
