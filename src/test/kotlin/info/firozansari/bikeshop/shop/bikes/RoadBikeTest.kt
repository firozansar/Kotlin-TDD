package info.firozansari.bikeshop.shop.bikes

import info.firozansari.bikeshop.shop.components.Crankset
import info.firozansari.bikeshop.shop.components.Frame
import info.firozansari.bikeshop.shop.components.Tyre
import info.firozansari.bikeshop.shop.components.Wheel
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class RoadBikeTest {
    var roadBike: RoadBike? = null
    var frame: Frame? = null
    var crankset: Crankset? = null
    var tyre: Tyre? = null
    var wheel: Wheel? = null
    @BeforeEach
    fun before() {
        frame = Frame("Cinelli", 52, "carbon", "road", "red", 300)
        crankset = Crankset("Shimano", 46, 30)
        roadBike = RoadBike(frame!!, crankset!!, 0, "road")
        tyre = Tyre("Michellin", 700, 23, 15)
        wheel = Wheel("Mavic", 23, 100)
    }

    @Test
    fun canAddTyre() {
        roadBike!!.addTyre(tyre!!)
        Assertions.assertEquals(1, roadBike!!.tyresCount())
    }

    @Test
    fun CantAddTyre() {
        roadBike!!.addTyre(tyre!!)
        roadBike!!.addTyre(tyre!!)
        roadBike!!.addTyre(tyre!!)
        roadBike!!.addTyre(tyre!!)
        roadBike!!.addTyre(tyre!!)
        Assertions.assertEquals(4, roadBike!!.tyresCount())
    }

    @Test
    fun canCalculatePrice() {
        roadBike!!.addTyre(tyre!!)
        roadBike!!.addTyre(tyre!!)
        roadBike!!.addTyre(tyre!!)
        roadBike!!.addTyre(tyre!!)
        roadBike!!.addWheel(wheel!!)
        roadBike!!.addWheel(wheel!!)
        roadBike!!.addWheel(wheel!!)
        roadBike!!.addWheel(wheel!!)
        Assertions.assertEquals(790, roadBike!!.price)
    }
}