package info.firozansari.fizzbuzz

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test


class FizzBuzzProcessorTest {
    @Test
    fun FizzBuzzNormalNumbers() {
        val fb = FizzBuzzProcessor
        assertEquals("1", fb.convert(1))
        assertEquals("2", fb.convert(2))
    }

    @Test
    fun FizzBuzzThreeNumbers() {
        val fb = FizzBuzzProcessor
        assertEquals("Fizz", fb.convert(3))
    }

    @Test
    fun FizzBuzzFiveNumbers() {
        val fb = FizzBuzzProcessor
        assertEquals("Buzz", fb.convert(5))
    }

    @Test
    fun FizzBuzzThreeAndFiveNumbers() {
        val fb = FizzBuzzProcessor
        assertEquals("Buzz", fb.convert(5))
    }
}