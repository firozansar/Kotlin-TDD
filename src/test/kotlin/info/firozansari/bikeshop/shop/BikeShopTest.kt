package info.firozansari.bikeshop.shop

import info.firozansari.bikeshop.shop.bikes.RoadBike
import info.firozansari.bikeshop.shop.components.Crankset
import info.firozansari.bikeshop.shop.components.Tyre
import info.firozansari.bikeshop.shop.components.Wheel
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions
import info.firozansari.bikeshop.people.Customer
import info.firozansari.bikeshop.people.Staff
import info.firozansari.bikeshop.shop.components.Frame

class BikeShopTest {
    private lateinit var customer: Customer
    private lateinit var staff: Staff
    private lateinit var frame: Frame
    private lateinit var crankset: Crankset
    private lateinit var roadBike: RoadBike
    private lateinit var tyre: Tyre
    private lateinit var wheel: Wheel
    private lateinit var bikeShop: BikeShop

    @BeforeEach
    fun before() {
        customer = Customer("Kat", 1000)
        staff = Staff("Kyle")
        bikeShop = BikeShop(staff, customer, 10000)
        frame = Frame("Cinelli", 52, "carbon", "road", "red", 300)
        crankset = Crankset("Shimano", 46, 30)
        roadBike = RoadBike(frame, crankset, 0, "road")
        tyre = Tyre("Michellin", 700, 23, 15)
        wheel = Wheel("Mavic", 23, 100)
    }

    @Test
    fun canBuy() {
        bikeShop.buy(roadBike)
        bikeShop.buy(tyre)
        Assertions.assertEquals(1, bikeShop.stockCount())
        Assertions.assertEquals(1, bikeShop.bikesCount())
    }

    @Test
    fun canSell() {
        bikeShop.buy(tyre)
        bikeShop.buy(frame)
        bikeShop.sell(tyre, customer)
        Assertions.assertEquals(1, customer.collectionCount())
        Assertions.assertEquals(985, customer.wallet)
        Assertions.assertEquals(10015, bikeShop.till)
        Assertions.assertEquals(1, bikeShop.stockCount())
    }
}