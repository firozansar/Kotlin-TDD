package info.firozansari.fizzbuzz

import kotlin.jvm.JvmStatic
import kotlin.jvm.JvmOverloads
import java.util.ArrayList

object FizzBuzzProcessor {
    private const val OUTPUT_FORMAT = "%32s"
    @JvmStatic
    fun main(args: Array<String>) {
        // Using right shift operator with negative num in Java
        var num = -2
        println(num)
        println("Before shift : " + Integer.toBinaryString(num))
        num = num shr 1
        // shifting 1 right bit
        println(num)
        println("After shift : " + Integer.toBinaryString(num))
        // Output: -2
        // Before shift : 11111111111111111111111111111110
        // -1
        // After shift : 11111111111111111111111111111111

        // Using unsigned right shift with negative number in Java
        // we can use binary literals from JDK 1.7 to assign
        // binary values to an integer, 0b is for binary, similar to 0x of hexadecimal
        var numb = -117769990
        println("Before unsigned right shift : " + Integer.toBinaryString(numb))
        println("number in decimal format : $numb")
        numb = numb ushr 1
        //shift 1 bit using right shift without sign
        println("After unsigned right shift : " + Integer.toBinaryString(numb))
        println("number in decimal format : $numb")
        val STRING_FORMAT = "%-10s = %s"
        val number = 24
        println("Positive Number")
        println(String.format(STRING_FORMAT, "Input $number", printBinary(number)))
        println(String.format(STRING_FORMAT, "$number >> 2", printBinary(number shr 2)))
        println(String.format(STRING_FORMAT, "$number >>> 2", printBinary(number ushr 2)))
        val number2 = -19
        println("\nNegative Number")
        println(String.format(STRING_FORMAT, "Input $number2", printBinary(number2)))
        println(String.format(STRING_FORMAT, "$number2 >> 2", printBinary(number2 shr 2)))
        println(String.format(STRING_FORMAT, "$number2 >>> 2", printBinary(number2 ushr 2)))

//        for (int i = 1; i <= 100; i++) {
//            System.out.println(convert(i));
//        }
    }

    @JvmOverloads
    fun printBinary(number: Int, blockSize: Int = 8, separator: String? = "|"): String {

        // pad leading zero
        val pBinary = String.format(OUTPUT_FORMAT, Integer.toBinaryString(number)).replace(" ", "0")

        // split by blockSize
        val result: MutableList<String> = ArrayList()
        var index = 0
        while (index < pBinary.length) {
            result.add(pBinary.substring(index, Math.min(index + blockSize, pBinary.length)))
            index += blockSize
        }
        return java.lang.String.join(separator, result)
    }

    fun convert(fizzBuzz: Int): String {
        if (fizzBuzz % 15 == 0) {
            return "FizzBuzz"
        }
        if (fizzBuzz % 3 == 0) {
            return "Fizz"
        }
        return if (fizzBuzz % 5 == 0) {
            "Buzz"
        } else fizzBuzz.toString()
    }
}