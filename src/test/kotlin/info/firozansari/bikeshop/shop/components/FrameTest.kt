package info.firozansari.bikeshop.shop.components

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class FrameTest {
    var frame: Frame? = null

    @BeforeEach
    fun before() {
        frame = Frame("Cinelli", 52, "carbon", "road", "red", 200)
    }

    @get:Test
    val material: Unit
        get() {
            Assertions.assertEquals("carbon", frame!!.material)
        }

    @get:Test
    val make: Unit
        get() {
            Assertions.assertEquals("Cinelli", frame!!.make)
        }
}