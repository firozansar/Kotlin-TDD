package info.firozansari.bikeshop.people

import info.firozansari.bikeshop.shop.bikes.RoadBike
import info.firozansari.bikeshop.shop.components.Crankset
import info.firozansari.bikeshop.shop.components.Frame
import info.firozansari.bikeshop.shop.components.Tyre
import info.firozansari.bikeshop.shop.components.Wheel
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class CustomerTest {
    var customer: Customer? = null
    var frame: Frame? = null
    var crankset: Crankset? = null
    var roadBike: RoadBike? = null
    var tyre: Tyre? = null
    var wheel: Wheel? = null
    @BeforeEach
    fun before() {
        customer = Customer("Kat", 1000)
        frame = Frame("Cinelli", 52, "carbon", "road", "red", 300)
        crankset = Crankset("Shimano", 46, 30)
        roadBike = RoadBike(frame!!, crankset!!, 0, "road")
        tyre = Tyre("Michellin", 700, 23, 15)
        wheel = Wheel("Mavic", 23, 100)
    }

    @Test
    fun canAddToBasket() {
        customer!!.addToBasket(frame!!)
        customer!!.addToBasket(roadBike!!)
        Assertions.assertEquals(2, customer!!.basketCount())
    }

    @Test
    fun canBuy() {
        customer!!.addToBasket(frame!!)
        customer!!.buy(frame)
        Assertions.assertEquals(700, customer!!.wallet)
        Assertions.assertEquals("I cycle on road bike, I am free!", roadBike!!.cycle())
    }
}