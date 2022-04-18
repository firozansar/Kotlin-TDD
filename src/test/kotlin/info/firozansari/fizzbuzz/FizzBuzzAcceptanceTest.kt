package info.firozansari.fizzbuzz

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.util.Arrays

class FizzBuzzAcceptanceTest {
    // Since we only have 100 numbers, how many ways could you automate the assertion of the full range of
    // numbers used?
    //
    // - sample
    // - different algorithm to calculate result
    // - hard-coded list of expected values
    // - algorithmically calculate the list of expected values
    // - compare against a file
    /*
    1 == 1
    2 == 2
    3 == fizz
    4 == 4
    5 == Buzz
    6 == Fizz
    7 == 7
    8 == 8
    9 == Fizz
    10 == Buzz
    ... 12
    15 == FizzBuzz
    100 == Buzz
 */
    // this uses a sampling model based on the agreed acceptance criteria
    @Test
    fun checkAcceptanceCriteria() {
        val fizzBuzz = FizzBuzzConverter()
        assertEquals("1", fizzBuzz.convert(1))
        assertEquals("2", fizzBuzz.convert(2))
        assertEquals("Fizz", fizzBuzz.convert(3))
        assertEquals("4", fizzBuzz.convert(4))
        assertEquals("Buzz", fizzBuzz.convert(5))
        assertEquals("Fizz", fizzBuzz.convert(6))
        assertEquals("7", fizzBuzz.convert(7))
        assertEquals("8", fizzBuzz.convert(8))
        assertEquals("Fizz", fizzBuzz.convert(9))
        assertEquals("Buzz", fizzBuzz.convert(10))
        assertEquals("11", fizzBuzz.convert(11))
        assertEquals("Fizz", fizzBuzz.convert(12))
        assertEquals("13", fizzBuzz.convert(13))
        assertEquals("14", fizzBuzz.convert(14))
        assertEquals("FizzBuzz", fizzBuzz.convert(15))
        assertEquals("16", fizzBuzz.convert(16))
        assertEquals("Buzz", fizzBuzz.convert(100))
    }

    // Write a program that prints the numbers from 1 to 100.
    // But for multiples of three print “Fizz” instead of the number
    // and for the multiples of five print “Buzz”.
    // For numbers which are multiples of both three and five print “FizzBuzz”
    // this uses an algorithmic model
    @Test
    fun checkAllNumbers() {
        var isMultipleOfThree = false
        var isMultipleOfFive = false
        val fizzBuzz = FizzBuzzConverter()
        for (checkThis in 1..100) {
            var expectedVal = checkThis.toString()
            isMultipleOfFive = if (checkThis % 5 == 0) true else false
            isMultipleOfThree = if (checkThis % 3 == 0) true else false
            if (isMultipleOfFive && isMultipleOfThree) {
                expectedVal = "FizzBuzz"
            } else {
                if (isMultipleOfFive) {
                    expectedVal = "Buzz"
                } else {
                    if (isMultipleOfThree) {
                        expectedVal = "Fizz"
                    }
                }
            }
            println(fizzBuzz.convert(checkThis))
            assertEquals(expectedVal, fizzBuzz.convert(checkThis))
        }
    }

    // comparison against pre-identified lists of numbers
    // Fizz Buzz = 15, 30, 45, 60, 75, 90
    // Buzz 5, 10,
    // Fizz 3, 6, 9, 12
    @Test
    fun checkAllNumbersPreCanned() {
        val fizzBuzz = arrayOf(15, 30, 45, 60, 75, 90)
        // creating this manually was prone to error - I missed out 35 and 100 originally
        val buzz = arrayOf(5, 10, 20, 25, 35, 40, 50, 55, 65, 70, 80, 85, 95, 100)
        // creating this array was a pain of mental arithmetic
        val fizz = arrayOf(
            3, 6, 9, 12, 18, 21, 24, 27, 33, 36, 39,
            42, 48, 51, 54, 57, 63, 66, 69, 72, 78,
            81, 84, 87, 93, 96, 99
        )
        val fizzBuzzer = FizzBuzzConverter()
        for (checkThis in 1..100) {
            var checkedIt = false
            if (Arrays.asList(*fizz).contains(checkThis)) {
                assertEquals("Fizz", fizzBuzzer.convert(checkThis))
                checkedIt = true
            }
            if (Arrays.asList(*buzz).contains(checkThis)) {
                assertEquals("Buzz", fizzBuzzer.convert(checkThis))
                checkedIt = true
            }
            if (Arrays.asList(*fizzBuzz).contains(checkThis)) {
                assertEquals("FizzBuzz", fizzBuzzer.convert(checkThis))
                checkedIt = true
            }
            if (!checkedIt) {
                assertEquals(checkThis.toString(), fizzBuzzer.convert(checkThis))
                checkedIt = true
            }
            assertEquals(true, checkedIt)
        }
    }

    // alternative way to compare all numbers by writing an algorithm to generate 3 different lists of multiples
    // i.e. multiples of 3*5, 3, and 5
    //
    // because calculating the numbers in advance was a pain I decided to write code to
    // generate them - this seemed simpler than recreating the algorithm for fizz buzz in a different way
    // also has the ability to scale up if I Want to do 1-1000 or 1- 1000000 by changing the value for `maxVal`
    @Test
    fun checkAllNumbersAlgorithmicallyGenerated() {
        val fizzBuzz: MutableList<Int> = ArrayList()
        val buzz: MutableList<Int> = ArrayList()
        val fizz: MutableList<Int> = ArrayList()

        // use an algorithm to calculate the values in advance
        val maxVal = 100
        for (i in 1 until maxVal) {

            // For numbers which are multiples of both three and five print “FizzBuzz”
            val fizzBuzzVal = i * (3 * 5)
            if (fizzBuzzVal <= maxVal) {
                fizzBuzz.add(fizzBuzzVal)
            }

            // and for the multiples of five print “Buzz”.
            val buzzVal = i * 5
            if (buzzVal <= maxVal) {
                if (!fizzBuzz.contains(buzzVal)) {
                    buzz.add(buzzVal)
                }
            }

            // But for multiples of three print “Fizz” instead of the number
            val fizzVal = i * 3
            if (fizzVal <= maxVal) {
                if (!fizzBuzz.contains(fizzVal)) {
                    fizz.add(fizzVal)
                }
            }
        }
        val fizzBuzzer = FizzBuzzConverter()
        for (checkThis in 1..maxVal) {
            var checkedIt = false
            if (fizz.contains(checkThis)) {
                assertEquals("Fizz", fizzBuzzer.convert(checkThis))
                checkedIt = true
            }
            if (buzz.contains(checkThis)) {
                assertEquals("Buzz", fizzBuzzer.convert(checkThis))
                checkedIt = true
            }
            if (fizzBuzz.contains(checkThis)) {
                assertEquals("FizzBuzz", fizzBuzzer.convert(checkThis))
                checkedIt = true
            }
            if (!checkedIt) {
                assertEquals(checkThis.toString(), fizzBuzzer.convert(checkThis))
                checkedIt = true
            }
            assertEquals(true, checkedIt)
            println(fizzBuzzer.convert(checkThis))
        }
    }
}
