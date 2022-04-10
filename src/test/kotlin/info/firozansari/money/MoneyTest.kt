package info.firozansari.money

import info.firozansari.money.Money.Factory.dollar
import info.firozansari.money.Money.Factory.franc
import info.firozansari.money.Money.Factory.pound
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class MoneyTest {

    @Test
    fun testMultiplication() {
        val five: Money = dollar(5)
        assertEquals(dollar(10), five.times(2))
        assertEquals(dollar(15), five.times(3))
    }

    @Test
    fun testEquality() {
        assertTrue(dollar(5) == dollar(5))
        assertFalse(dollar(5) == dollar(6))
        assertFalse(franc(5) == dollar(5))
        assertFalse(pound(5) == dollar(5))
    }

    @Test
    fun testCurrency() {
        assertEquals("USD", dollar(1).currency())
        assertEquals("CHF", franc(1).currency())
        assertEquals("GBP", pound(1).currency())
    }

    @Test
    fun testSimpleAddition() {
        val five = dollar(5)
        val sum = five.plus(five)
        val reduced = Bank().reduce(sum, "USD")
        assertEquals(dollar(10), reduced)
    }

    @Test
    fun testPlusReturnSum() {
        val five = dollar(5)
        val sum = five.plus(five) as Sum
        assertEquals(five, sum.augend)
        assertEquals(five, sum.addend)
    }

    @Test
    fun testReduceSum() {
        val sum = Sum(dollar(3), dollar(4))
        val result = Bank().reduce(sum, "USD")
        assertEquals(dollar(7), result)
    }

    @Test
    fun testReduceMoney() {
        val result = Bank().reduce(dollar(1), "USD")
        assertEquals(dollar(1), result)
    }

    @Test
    fun testReduceMoneyDifferentCurrency() {
        val bank = Bank()
        bank.addRate("CHF", "USD", 2)
        val result = bank.reduce(franc(2), "USD")
        assertEquals(dollar(1), result)
    }

    @Test
    fun testIdentityRate() {
        assertEquals(1, Bank().rate("USD", "USD"))
    }

    @Test
    fun testMixedAddition() {
        val fiveBucks: Expression = dollar(5)
        val tenFrancs: Expression = franc(10)
        val bank = Bank()
        bank.addRate("CHF", "USD", 2)
        val result = bank.reduce(fiveBucks.plus(tenFrancs), "USD")
        assertEquals(dollar(10), result)
    }

    @Test
    fun testSumPlusMoney() {
        val fiveBucks: Expression = dollar(5)
        val tenFrancs: Expression = franc(10)
        val bank = Bank()
        bank.addRate("CHF", "USD", 2)
        val sum = Sum(fiveBucks, tenFrancs).plus(fiveBucks)
        val result = bank.reduce(sum, "USD")
        assertEquals(dollar(15), result)
    }

    @Test
    fun testSumTimes() {
        val fiveBucks: Expression = dollar(5)
        val tenFrancs: Expression = franc(10)
        val bank = Bank()
        bank.addRate("CHF", "USD", 2)
        val sum = Sum(fiveBucks, tenFrancs).times(2)
        val result = bank.reduce(sum, "USD")
        assertEquals(dollar(20), result)
    }
}
