package info.firozansari.bikeshop.shop.components

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class FrameTest {
    private lateinit var frame: Frame

    @BeforeEach
    fun before() {
        frame = Frame("Cinelli", 52, "carbon", "road", "red", 200)
    }

    @get:Test
    val material: Unit
        get() {
            assertEquals("carbon", frame.material)
        }

    @get:Test
    val make: Unit
        get() {
            assertEquals("Cinelli", frame.make)
        }
}