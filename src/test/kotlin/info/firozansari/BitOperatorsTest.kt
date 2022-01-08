package info.firozansari

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertEquals

class BitOperatorsTest {
    @Test
    fun complement_positive() {
        val i = 189
        assertEquals(i, 189)
        val complementedI = i.inv()
        assertEquals(Integer.toBinaryString(complementedI), "11111111111111111111111101000010")
        assertEquals(complementedI, -190)
    }

    @Test
    fun complement_negative() {
        val i = -1
        assertEquals(i, -1)
        val complementedI = i.inv()
        assertEquals(Integer.toBinaryString(complementedI), "0")
        assertEquals(complementedI, 0)
    }

    @Test
    fun signedLeftShift_positive() {
        val i = 189
        assertEquals(i, 189)
        val leftShiftedI = i shl 2
        assertEquals(Integer.toBinaryString(leftShiftedI), "1011110100") // 10111101_00
        assertEquals(leftShiftedI, 756)
    }

    @Test
    fun signedLeftShift_negative() {
        val i = -2000000123
        assertEquals(i, -2000000123)
        val leftShiftedI = i shl 2
        assertEquals(
            Integer.toBinaryString(leftShiftedI),
            "100011001010011010111000010100"
        ) // 100011001010011010111000010100_00
        assertEquals(leftShiftedI, 589934100)
    }

    @Test
    fun unsignedRightShift_positive() {
        val i = 189
        assertEquals(i, 189)
        val rightShiftedI = i ushr 2
        assertEquals(Integer.toBinaryString(rightShiftedI), "101111")
        assertEquals(rightShiftedI, 47)
    }

    @Test
    fun unsignedRightShift_negative() {
        val i = -2000000123
        assertEquals(i, -2000000123)
        val rightShiftedI = i ushr 2
        assertEquals(Integer.toBinaryString(rightShiftedI), "100010001100101001101011100001")
        assertEquals(rightShiftedI, 573741793)
    }

    @Test
    fun and() {
        val x = 18
        val y = 35
        val and = x and y
        assertEquals(Integer.toBinaryString(and), "10")
        assertEquals(and, 2)
    }

    @Test
    fun exclusiveOr() {
        val x = 18
        val y = 35
        val or = x xor y
        assertEquals(Integer.toBinaryString(or), "110001")
        assertEquals(or, 49)
    }

    @Test
    fun inclusiveOr() {
        val x = 18
        val y = 35
        val or = x or y
        assertEquals(Integer.toBinaryString(or), "110011")
        assertEquals(or, 51)
    }
}