package info.firozansari.bikeshop.shop.components

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class TyreTest {
    var tyre: Tyre? = null
    @BeforeEach
    fun before() {
        tyre = Tyre("Michellin", 700, 23, 10)
    }

    @Test
    fun hasSize() {
        Assertions.assertEquals(700, tyre!!.size)
    }
}