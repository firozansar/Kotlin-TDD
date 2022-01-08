package info.firozansari.calculator

import info.firozansari.calculator.PostalRateCalculator.main
import java.io.ByteArrayOutputStream
import java.io.PrintStream
import org.junit.jupiter.api.BeforeEach
import kotlin.Throws
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions
import java.lang.Exception

class PostalRateCalculatorTest {
    private var specialOut // Special stream to direct output
            : ByteArrayOutputStream? = null
    private var ps: PrintStream? = null
    private var console // Output console
            : PrintStream? = null

    @BeforeEach
    @Throws(Exception::class)
    fun setUp() {
        specialOut = ByteArrayOutputStream()
        ps = PrintStream(specialOut)
        console = System.out
        System.setOut(ps)
    }

    @AfterEach
    @Throws(Exception::class)
    fun tearDown() {
        System.setOut(console)
    }

    @Test
    fun tooFewArguments() {
        val exInputs = arrayOf("example", "arguments", "being", "entered")
        main(exInputs)
        Assertions.assertEquals(
            "Usage: FromPostalCode, ToPostalCode, Length (cm), Width (cm), Height (cm), Weight (kg), PostType",
            specialOut.toString()
        )
    }

    @Test
    fun tooManyArguments() {
        val exInputs = arrayOf("too", "many", "arguments", "are", "filling", "up", "the", "command", "line")
        main(exInputs)
        Assertions.assertEquals(
            "Usage: FromPostalCode, ToPostalCode, Length (cm), Width (cm), Height (cm), Weight (kg), PostType",
            specialOut.toString()
        )
    }

    @Test
    fun negativeHeight() {
        val sample = arrayOf("H1S 1C4", "H1S 1C4", "50.0", "40.0", "-10.0", "7.5", "Regular")
        main(sample)
        Assertions.assertEquals("Measurements must be positive!", specialOut.toString())
    }

    @Test
    fun negativeWeight() {
        val sample = arrayOf("H1S 1C4", "H1S 1C4", "50.0", "40.0", "30.0", "-7.5", "Regular")
        main(sample)
        Assertions.assertEquals("Measurements must be positive!", specialOut.toString())
    }

    @Test
    fun negativeWidth() {
        val sample = arrayOf("H1S 1C4", "H1S 1C4", "50.0", "-40.0", "30.0", "7.5", "Regular")
        main(sample)
        Assertions.assertEquals("Measurements must be positive!", specialOut.toString())
    }

    @Test
    fun negativeLength() {
        val sample = arrayOf("H1S 1C4", "H1S 1C4", "-50.0", "40.0", "30.0", "7.5", "Regular")
        main(sample)
        Assertions.assertEquals("Measurements must be positive!", specialOut.toString())
    }

    @Test
    fun heightNotNumber() {
        val sample = arrayOf("H1S 1C4", "H1S 1C4", "50.0", "40.0", "height", "7.5", "Regular")
        main(sample)
        Assertions.assertEquals("Measurements must be numbers!", specialOut.toString())
    }

    @Test
    fun weightNotNumber() {
        val sample = arrayOf("H1S 1C4", "H1S 1C4", "50.0", "40.0", "30.0", "weight", "Regular")
        main(sample)
        Assertions.assertEquals("Measurements must be numbers!", specialOut.toString())
    }

    @Test
    fun lengthNotNumber() {
        val sample = arrayOf("H1S 1C4", "H1S 1C4", "length", "40.0", "30.0", "8.5", "Regular")
        main(sample)
        Assertions.assertEquals("Measurements must be numbers!", specialOut.toString())
    }

    @Test
    fun widthNotNumber() {
        val sample = arrayOf("H1S 1C4", "H1S 1C4", "45.5", "width", "30.0", "8.5", "Regular")
        main(sample)
        Assertions.assertEquals("Measurements must be numbers!", specialOut.toString())
    }

    @get:Test
    val isLengthLongestSide: Unit
        get() {
            val sample = arrayOf("H1S 1C4", "H1S 1C4", "20.0", "30.0", "30.0", "8.5", "Regular")
            main(sample)
            Assertions.assertEquals("Length must be longest side of parcel!", specialOut.toString())
        }

    @Test
    fun exceedMaxLength() {
        val sample = arrayOf("H1S 1C4", "H1S 1C4", "225.7", "40.0", "30.0", "7.5", "Regular")
        main(sample)
        Assertions.assertEquals("Length must not exceed 200.0 cm!", specialOut.toString())
    }

    @Test
    fun belowMinWidth() {
        val sample = arrayOf("H1S 1C4", "H1S 1C4", "50.0", "0.05", "30.0", "7.5", "Regular")
        main(sample)
        Assertions.assertEquals("Measurements must be at least 0.10 cm!", specialOut.toString())
    }

    @Test
    fun belowMinLengthandHeight() {
        val sample = arrayOf("H1S 1C4", "H1S 1C4", "0.07", "0.05", "0.05", "7.5", "Regular")
        main(sample)
        Assertions.assertEquals("Measurements must be at least 0.10 cm!", specialOut.toString())
    }

    @Test
    fun exceedMaxWeight() {
        val sample = arrayOf("H1S 1C4", "H1S 1C4", "50.0", "40.0", "30.0", "32.68", "Regular")
        main(sample)
        Assertions.assertEquals("Weight must not exceed 30.0 kg!", specialOut.toString())
    }

    @Test
    fun exceedGirthPlusLengthLimit() {
        val sample = arrayOf("H1S 1C4", "H1S 1C4", "75.0", "75.0", "75.0", "12.46", "Regular")
        main(sample)
        Assertions.assertEquals("Length plus girth must not exceed 300.0 cm!", specialOut.toString())
    }

    @Test
    fun incorrectPostType() {
        val sample = arrayOf("H1S 1C4", "H1S 1C4", "50.0", "40.0", "30.0", "22.83", "Bob42")
        main(sample)
        Assertions.assertEquals("Post Type must be either: Regular, Xpress or Priority", specialOut.toString())
    }

    @Test
    fun incorrectStringLengthFromPC() {
        val sample = arrayOf("HS 1C4", "H1S 1C4", "25.0", "25.0", "25.0", "10.83", "Regular")
        main(sample)
        Assertions.assertEquals("From postal code must have a length of 6", specialOut.toString())
    }

    @Test
    fun incorrectStringLengthToPC() {
        val sample = arrayOf("H1S 1C4", "HS 1C4", "25.0", "25.0", "25.0", "10.83", "Regular")
        main(sample)
        Assertions.assertEquals("To postal code must have a length of 6", specialOut.toString())
    }

    @Test
    fun incorrectFormatFromPC() {
        val sample = arrayOf("H12 1C4", "H1S 1C4", "25.0", "25.0", "25.0", "10.83", "Regular")
        main(sample)
        Assertions.assertEquals(
            "From postal code should have this format: letter number letter number letter number",
            specialOut.toString()
        )
    }

    @Test
    fun incorrectFormatToPC() {
        val sample = arrayOf("H1S 1C4", "H21 1C4", "25.0", "25.0", "25.0", "10.83", "Regular")
        main(sample)
        Assertions.assertEquals(
            "To postal code should have this format: letter number letter number letter number",
            specialOut.toString()
        )
    }

    @Test
    fun ValidationForRegularPrice() {
        val sample = arrayOf("H1S 1C4", "H2S 1Z4", "25.0", "25.0", "25.0", "10.83", "Regular")
        main(sample)
        Assertions.assertEquals("Regular Price is:$13.32", specialOut.toString())
    }

    @Test
    fun ValidationForXpressPrice() {
        val sample = arrayOf("H1S 1C4", "H2S 1Z4", "25.0", "25.0", "25.0", "10.83", "Xpress")
        main(sample)
        Assertions.assertEquals("Xpress Price is:$17.98", specialOut.toString())
    }

    @Test
    fun ValidationForPriorityPrice() {
        val sample = arrayOf("H1S 1C4", "H2S 1Z4", "25.0", "25.0", "25.0", "10.83", "Priority")
        main(sample)
        Assertions.assertEquals("Priority Price is:$30.18", specialOut.toString())
    }

}