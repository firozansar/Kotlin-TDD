package info.firozansari.bikeshop.shop.components

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class TyreTest {
    private lateinit var tyre: Tyre

    @BeforeEach
    fun before() {
        tyre = Tyre("Michellin", 700, 23, 10)
    }

    @Test
    fun hasSize() {
        assertEquals(700, tyre.size)
    }
}