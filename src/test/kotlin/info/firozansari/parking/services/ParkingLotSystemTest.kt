package info.firozansari.parking.services

import info.firozansari.parking.enums.Car
import info.firozansari.parking.enums.CarCompany
import info.firozansari.parking.enums.DriverType
import info.firozansari.parking.enums.VehicleColor
import info.firozansari.parking.exception.ParkingLotException
import info.firozansari.parking.model.VehicleDetails
import info.firozansari.parking.observer.AirportSecurity
import info.firozansari.parking.observer.ParkingLotOwner
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.time.LocalTime
import java.util.Arrays

class ParkingLotSystemTest {
    private lateinit var parkingLotSystem: ParkingLotSystem

    @BeforeEach
    fun setUp() {
        parkingLotSystem = ParkingLotSystem(3, 1)
    }

    @Test
    fun givenVehicle_WhenParked_ShouldReturnTrue() {
        parkingLotSystem.parkVehicle(
            VehicleDetails(
                "vehicle", DriverType.NORMAL_DRIVER,
                Car.SMALL_CAR
            ),
            "AA"
        )
        val isParked = parkingLotSystem!!.isVehicleParked("vehicle")
        assertTrue(isParked)
    }

    @Test
    @Throws(ParkingLotException::class)
    fun givenVehicleParked_WhenAlreadyParked_ShouldThrowException() {
        assertThrows<ParkingLotException> {
            parkingLotSystem.parkVehicle(
                VehicleDetails(
                    "vehicle", DriverType.NORMAL_DRIVER,
                    Car.SMALL_CAR
                ),
                "AA"
            )
            parkingLotSystem.parkVehicle(
                VehicleDetails(
                    "vehicle", DriverType.NORMAL_DRIVER,
                    Car.SMALL_CAR
                ),
                "AA"
            )
        }
    }

    @Test
    fun givenVehicle_WhenUnParked_ShouldReturnTrue() {
        parkingLotSystem.parkVehicle(
            VehicleDetails(
                "vehicle", DriverType.NORMAL_DRIVER,
                Car.SMALL_CAR
            ),
            "AA"
        )
        val isUnParked = parkingLotSystem!!.unPark("vehicle")
        assertTrue(isUnParked)
    }

    @Test
    fun givenAnotherVehicle_WhenUnParked_ShouldReturnFalse() {
        parkingLotSystem.parkVehicle(
            VehicleDetails(
                "vehicle", DriverType.NORMAL_DRIVER,
                Car.SMALL_CAR
            ),
            "AA"
        )
        val isUnParked = parkingLotSystem!!.unPark("vehicle1")
        assertFalse(isUnParked)
    }

    @Test
    @Throws(ParkingLotException::class)
    fun givenVehicleParked_WhenLotFull_ShouldThrowException() {
        assertThrows<ParkingLotException> {
            parkingLotSystem.parkVehicle(
                VehicleDetails(
                    "vehicle1", DriverType.NORMAL_DRIVER,
                    Car.SMALL_CAR
                ),
                "AA"
            )
            parkingLotSystem.parkVehicle(
                VehicleDetails(
                    "vehicle2", DriverType.NORMAL_DRIVER,
                    Car.SMALL_CAR
                ),
                "AA"
            )
            parkingLotSystem.parkVehicle(
                VehicleDetails(
                    "vehicle3", DriverType.NORMAL_DRIVER,
                    Car.SMALL_CAR
                ),
                "AA"
            )
            parkingLotSystem.parkVehicle(
                VehicleDetails(
                    "vehicle4", DriverType.NORMAL_DRIVER,
                    Car.SMALL_CAR
                ),
                "AA"
            )
        }
    }

    @Test
    @Throws(ParkingLotException::class)
    fun givenParkingLot_WhenFull_ShouldRedirectSecurityStaff() {
        assertThrows<ParkingLotException> {
            val airportSecurity = AirportSecurity()
            parkingLotSystem.register(airportSecurity)

            parkingLotSystem.parkVehicle(
                VehicleDetails(
                    "vehicle1", DriverType.NORMAL_DRIVER,
                    Car.SMALL_CAR
                ),
                "AA"
            )
            parkingLotSystem.parkVehicle(
                VehicleDetails(
                    "vehicle2", DriverType.NORMAL_DRIVER,
                    Car.SMALL_CAR
                ),
                "AA"
            )
            parkingLotSystem.parkVehicle(
                VehicleDetails(
                    "vehicle3", DriverType.NORMAL_DRIVER,
                    Car.SMALL_CAR
                ),
                "AA"
            )
            parkingLotSystem.parkVehicle(
                VehicleDetails(
                    "vehicle4", DriverType.NORMAL_DRIVER,
                    Car.SMALL_CAR
                ),
                "AA"
            )

            val capacityFull = airportSecurity.isCapacityFull
            assertTrue(capacityFull)
        }
    }

    @Test
    fun givenParkingLot_WhenNotFull_ShouldNotBeRedirectSecurityStaff() {
        val airportSecurity = AirportSecurity()
        parkingLotSystem.register(airportSecurity)

        parkingLotSystem.parkVehicle(
            VehicleDetails(
                "vehicle3", DriverType.NORMAL_DRIVER,
                Car.SMALL_CAR
            ),
            "AA"
        )
        parkingLotSystem.parkVehicle(
            VehicleDetails(
                "vehicle4", DriverType.NORMAL_DRIVER,
                Car.SMALL_CAR
            ),
            "AA"
        )
        parkingLotSystem.unPark("vehicle3")
        val capacityFull = airportSecurity.isCapacityFull
        assertFalse(capacityFull)
    }

    @Test
    @Throws(ParkingLotException::class)
    fun givenWhenLotIsFull_ShouldInformOwner() {
        assertThrows<ParkingLotException> {
            val parkingLotOwner = ParkingLotOwner()
            parkingLotSystem.register(parkingLotOwner)

            parkingLotSystem.parkVehicle(
                VehicleDetails(
                    "vehicle1", DriverType.NORMAL_DRIVER,
                    Car.SMALL_CAR
                ),
                "AA"
            )
            parkingLotSystem.parkVehicle(
                VehicleDetails(
                    "vehicle2", DriverType.NORMAL_DRIVER,
                    Car.SMALL_CAR
                ),
                "AA"
            )
            parkingLotSystem.parkVehicle(
                VehicleDetails(
                    "vehicle3", DriverType.NORMAL_DRIVER,
                    Car.SMALL_CAR
                ),
                "AA"
            )
            parkingLotSystem.parkVehicle(
                VehicleDetails(
                    "vehicle4", DriverType.NORMAL_DRIVER,
                    Car.SMALL_CAR
                ),
                "AA"
            )

            val capacityFull = parkingLotOwner.isCapacityFull
            assertTrue(capacityFull)
        }
    }

    @Test
    fun givenWhenLotIsEmpty_ShouldInformOwner() {
        val parkingLotOwner = ParkingLotOwner()

        parkingLotSystem.register(parkingLotOwner)

        parkingLotSystem.parkVehicle(
            VehicleDetails(
                "vehicle2", DriverType.NORMAL_DRIVER,
                Car.SMALL_CAR
            ),
            "AA"
        )
        parkingLotSystem.parkVehicle(
            VehicleDetails(
                "vehicle3", DriverType.NORMAL_DRIVER,
                Car.SMALL_CAR
            ),
            "AA"
        )
        parkingLotSystem.parkVehicle(
            VehicleDetails(
                "vehicle4", DriverType.NORMAL_DRIVER,
                Car.SMALL_CAR
            ),
            "AA"
        )
        parkingLotSystem.unPark("vehicle2")

        val capacityFull = parkingLotOwner.isCapacityFull
        assertFalse(capacityFull)
    }

    @Test
    fun givenVehicle_ShouldPark_OnAvailableSlot() {
        parkingLotSystem.parkVehicle(
            VehicleDetails(
                "vehicle2", DriverType.NORMAL_DRIVER,
                Car.SMALL_CAR
            ),
            "AA"
        )
        parkingLotSystem.parkVehicle(
            VehicleDetails(
                "vehicle3", DriverType.NORMAL_DRIVER,
                Car.SMALL_CAR
            ),
            "AA"
        )
    }

    @Test
    fun givenVehicle_WhenParkedShouldFindTheLocation() {
        parkingLotSystem.parkVehicle(
            VehicleDetails(
                "vehicle", DriverType.NORMAL_DRIVER,
                Car.SMALL_CAR
            ),
            "AA"
        )
        val vehicleLocation = parkingLotSystem.findVehicleLocation("vehicle")
        assertEquals(0, vehicleLocation)
    }

    @Test
    @Throws(ParkingLotException::class)
    fun givenVehicle_WhenNotFound_ShouldThrowException() {
        assertThrows<ParkingLotException> {
            parkingLotSystem.findVehicleLocation("vehicle2")
        }
    }

    @Test
    fun givenVehicle_WhenParked_ShouldReturnTime() {
        parkingLotSystem.parkVehicle(
            VehicleDetails(
                "vehicle", DriverType.NORMAL_DRIVER,
                Car.SMALL_CAR
            ),
            "AA"
        )
        val parkTime = parkingLotSystem.getParkTime("vehicle")
        assertEquals(parkTime, LocalTime.now().withNano(0))
    }

    @Test
    @Throws(ParkingLotException::class)
    fun givenVehicle_WhenNotParked_ShouldThrowException() {
        assertThrows<ParkingLotException> {
            parkingLotSystem.parkVehicle(
                VehicleDetails(
                    "vehicle1", DriverType.NORMAL_DRIVER,
                    Car.SMALL_CAR
                ),
                "AA"
            )
            val parkTime = parkingLotSystem.getParkTime("vehicle2")
            assertEquals(parkTime, LocalTime.now().withNano(0))
        }
    }

    @Test
    fun givenVehicle_WhenParkedAndUnParkedInLot_ShouldEvenlyDistributed() {
        val parkingLotSystem = ParkingLotSystem(3, 3)

        parkingLotSystem.parkVehicle(
            VehicleDetails(
                "vehicle1", DriverType.NORMAL_DRIVER,
                Car.SMALL_CAR
            ),
            "AA"
        )
        parkingLotSystem.parkVehicle(
            VehicleDetails(
                "vehicle2", DriverType.NORMAL_DRIVER,
                Car.SMALL_CAR
            ),
            "AA"
        )
        parkingLotSystem.parkVehicle(
            VehicleDetails(
                "vehicle3", DriverType.NORMAL_DRIVER,
                Car.SMALL_CAR
            ),
            "AA"
        )
        parkingLotSystem.parkVehicle(
            VehicleDetails(
                "vehicle4", DriverType.NORMAL_DRIVER,
                Car.SMALL_CAR
            ),
            "AA"
        )
        parkingLotSystem.parkVehicle(
            VehicleDetails(
                "vehicle5", DriverType.NORMAL_DRIVER,
                Car.SMALL_CAR
            ),
            "AA"
        )
        parkingLotSystem.parkVehicle(
            VehicleDetails(
                "vehicle6", DriverType.NORMAL_DRIVER,
                Car.SMALL_CAR
            ),
            "AA"
        )
        parkingLotSystem.parkVehicle(
            VehicleDetails(
                "vehicle7", DriverType.NORMAL_DRIVER,
                Car.SMALL_CAR
            ),
            "AA"
        )
        parkingLotSystem.unPark("vehicle7")
        val position = parkingLotSystem.vehiclePosition("vehicle3")
        assertEquals("Lot2 Slot0", position)
    }

    @Test
    fun givenVehicleToPark_WhenDriverIsHandicap_ShouldParkVehicleAtNearestSpot() {
        val parkingLotSystem = ParkingLotSystem(3, 3)
        parkingLotSystem.parkVehicle(
            VehicleDetails(
                "vehicle1", DriverType.NORMAL_DRIVER,
                Car.SMALL_CAR
            ),
            "AA"
        )
        parkingLotSystem.parkVehicle(
            VehicleDetails(
                "vehicle2", DriverType.NORMAL_DRIVER,
                Car.SMALL_CAR
            ),
            "AA"
        )
        parkingLotSystem.parkVehicle(
            VehicleDetails(
                "vehicle3", DriverType.NORMAL_DRIVER,
                Car.SMALL_CAR
            ),
            "AA"
        )
        parkingLotSystem.unPark("vehicle2")
        parkingLotSystem.parkVehicle(
            VehicleDetails(
                "vehicle4", DriverType.HANDICAP_DRIVER,
                Car.SMALL_CAR
            ),
            "AA"
        )
        parkingLotSystem.parkVehicle(
            VehicleDetails(
                "vehicle5", DriverType.HANDICAP_DRIVER,
                Car.SMALL_CAR
            ),
            "AA"
        )
        parkingLotSystem.parkVehicle(
            VehicleDetails(
                "vehicle6", DriverType.NORMAL_DRIVER,
                Car.SMALL_CAR
            ),
            "AA"
        )
        parkingLotSystem.parkVehicle(
            VehicleDetails(
                "vehicle7", DriverType.NORMAL_DRIVER,
                Car.SMALL_CAR
            ),
            "AA"
        )
        parkingLotSystem.parkVehicle(
            VehicleDetails(
                "vehicle8", DriverType.NORMAL_DRIVER,
                Car.SMALL_CAR
            ),
            "AA"
        )
        parkingLotSystem.parkVehicle(
            VehicleDetails(
                "vehicle9", DriverType.NORMAL_DRIVER,
                Car.SMALL_CAR
            ),
            "AA"
        )
        parkingLotSystem.unPark("vehicle7")
        parkingLotSystem.parkVehicle(
            VehicleDetails(
                "vehicleH", DriverType.HANDICAP_DRIVER,
                Car.SMALL_CAR
            ),
            "AA"
        )
        val position = parkingLotSystem.vehiclePosition("vehicle4")
        assertEquals("Lot0 Slot1", position)
    }

    @Test
    fun givenVehicleToPark_WhenDriverIsHandicap_AndCarIsLarge_ShouldReturnExpectedSlotNumbers() {
        val parkingLotSystem = ParkingLotSystem(3, 3)

        parkingLotSystem.parkVehicle(
            VehicleDetails(
                "vehicle1", DriverType.NORMAL_DRIVER,
                Car.SMALL_CAR
            ),
            "AA"
        )
        parkingLotSystem.parkVehicle(
            VehicleDetails(
                "vehicle2", DriverType.NORMAL_DRIVER,
                Car.SMALL_CAR
            ),
            "AA"
        )
        parkingLotSystem.parkVehicle(
            VehicleDetails(
                "vehicle3", DriverType.NORMAL_DRIVER,
                Car.SMALL_CAR
            ),
            "AA"
        )
        parkingLotSystem.parkVehicle(
            VehicleDetails(
                "vehicle4", DriverType.HANDICAP_DRIVER,
                Car.SMALL_CAR
            ),
            "AA"
        )
        parkingLotSystem.parkVehicle(
            VehicleDetails(
                "vehicle5", DriverType.HANDICAP_DRIVER,
                Car.LARGE_CAR
            ),
            "AA"
        )
        val position = parkingLotSystem.vehiclePosition("vehicle5")
        assertEquals("Lot1 Slot1", position)
    }

    @Test
    fun givenVehicleToPark_WhenDriverIsHandicap_AndCarIsMedium_ShouldReturnExpectedSlotNumbers() {
        val parkingLotSystem = ParkingLotSystem(3, 3)

        parkingLotSystem.parkVehicle(
            VehicleDetails(
                "vehicle1", DriverType.NORMAL_DRIVER,
                Car.SMALL_CAR
            ),
            "AA"
        )
        parkingLotSystem.parkVehicle(
            VehicleDetails(
                "vehicle2", DriverType.NORMAL_DRIVER,
                Car.SMALL_CAR
            ),
            "AA"
        )
        parkingLotSystem.parkVehicle(
            VehicleDetails(
                "vehicle3", DriverType.NORMAL_DRIVER,
                Car.SMALL_CAR
            ),
            "AA"
        )
        parkingLotSystem.parkVehicle(
            VehicleDetails(
                "vehicle4", DriverType.NORMAL_DRIVER,
                Car.SMALL_CAR
            ),
            "AA"
        )
        parkingLotSystem.parkVehicle(
            VehicleDetails(
                "vehicle5", DriverType.HANDICAP_DRIVER,
                Car.LARGE_CAR
            ),
            "AA"
        )
        parkingLotSystem.parkVehicle(
            VehicleDetails(
                "vehicle6", DriverType.HANDICAP_DRIVER,
                Car.SMALL_CAR
            ),
            "AA"
        )
        val position = parkingLotSystem.vehiclePosition("vehicle6")
        assertEquals("Lot0 Slot2", position)
    }

    @Test
    fun givenVehicleToPark_WhenDriverIsNormal_AndCarIsLarge_ShouldReturnExpectedSlotNumbers() {
        val parkingLotSystem = ParkingLotSystem(3, 3)

        parkingLotSystem.parkVehicle(
            VehicleDetails(
                "vehicle1", DriverType.NORMAL_DRIVER,
                Car.SMALL_CAR
            ),
            "AA"
        )
        parkingLotSystem.parkVehicle(
            VehicleDetails(
                "vehicle2", DriverType.NORMAL_DRIVER,
                Car.SMALL_CAR
            ),
            "AA"
        )
        parkingLotSystem.parkVehicle(
            VehicleDetails(
                "vehicle3", DriverType.NORMAL_DRIVER,
                Car.SMALL_CAR
            ),
            "AA"
        )
        parkingLotSystem.parkVehicle(
            VehicleDetails(
                "vehicle4", DriverType.NORMAL_DRIVER,
                Car.SMALL_CAR
            ),
            "AA"
        )
        parkingLotSystem.parkVehicle(
            VehicleDetails(
                "vehicle5", DriverType.NORMAL_DRIVER,
                Car.LARGE_CAR
            ),
            "AA"
        )
        parkingLotSystem.parkVehicle(
            VehicleDetails(
                "vehicle6", DriverType.NORMAL_DRIVER,
                Car.SMALL_CAR
            ),
            "AA"
        )
        val position = parkingLotSystem.vehiclePosition("vehicle5")
        assertEquals("Lot1 Slot1", position)
    }

    // UC12
    @Test
    fun givenVehicleToPark_WhenHaveWhiteColor_ShouldReturnExpectedSlotNumbers() {
        val parkingLotSystem = ParkingLotSystem(3, 3)

        parkingLotSystem.parkVehicle(
            VehicleDetails(
                "vehicle1", DriverType.NORMAL_DRIVER,
                Car.SMALL_CAR, VehicleColor.NO_COLOR
            ),
            "AA"
        )
        parkingLotSystem.parkVehicle(
            VehicleDetails(
                "vehicle2", DriverType.NORMAL_DRIVER,
                Car.SMALL_CAR, VehicleColor.WHITE
            ),
            "AA"
        )
        parkingLotSystem.parkVehicle(
            VehicleDetails(
                "vehicle3", DriverType.NORMAL_DRIVER,
                Car.SMALL_CAR, VehicleColor.NO_COLOR
            ),
            "AA"
        )
        parkingLotSystem.parkVehicle(
            VehicleDetails(
                "vehicle4", DriverType.NORMAL_DRIVER,
                Car.SMALL_CAR, VehicleColor.WHITE
            ),
            "AA"
        )
        val whiteColorVehicle = parkingLotSystem.getLocationOfWhiteVehicle(VehicleColor.WHITE)
        assertEquals(Arrays.asList("Lot0 Slot1", "Lot1 Slot0"), whiteColorVehicle)
    }

    @Test
    fun givenToyotaCar_WhenHaveBlueColor_ShouldReturnExpectedLocation() {
        val parkingLotSystem = ParkingLotSystem(3, 3)

        parkingLotSystem.parkVehicle(
            VehicleDetails(
                "MH-65-KS-8765", DriverType.NORMAL_DRIVER,
                VehicleColor.NO_COLOR, CarCompany.NISSAN
            ),
            "AA"
        )
        parkingLotSystem.parkVehicle(
            VehicleDetails(
                "MH-68-KS-8776", DriverType.NORMAL_DRIVER,
                VehicleColor.BLUE, CarCompany.TOYOTA
            ),
            "AA"
        )
        parkingLotSystem.parkVehicle(
            VehicleDetails(
                "MH-65-KS-8754", DriverType.NORMAL_DRIVER,
                VehicleColor.WHITE, CarCompany.NISSAN
            ),
            "AA"
        )
        parkingLotSystem.parkVehicle(
            VehicleDetails(
                "MH-65-KS-8654", DriverType.NORMAL_DRIVER,
                VehicleColor.BLUE, CarCompany.NISSAN
            ),
            "AA"
        )
        val carDetail = parkingLotSystem.getLocationOfVehicleByGivingColorAndBrand(
            VehicleColor.BLUE,
            CarCompany.TOYOTA
        )
        assertEquals(Arrays.asList("Lot1 Slot0 MH-68-KS-8776 AA"), carDetail)
    }

    @Test
    fun givenBMWCar_WhenParked_ShouldReturnTotalCountOFBMWCar() {
        val parkingLotSystem = ParkingLotSystem(3, 3)

        parkingLotSystem.parkVehicle(
            VehicleDetails(
                "MH-65-KS-8765", DriverType.NORMAL_DRIVER,
                VehicleColor.NO_COLOR, CarCompany.NISSAN
            ),
            "AA"
        )
        parkingLotSystem.parkVehicle(
            VehicleDetails(
                "MH-68-KS-8776", DriverType.NORMAL_DRIVER,
                VehicleColor.BLUE, CarCompany.BMW
            ),
            "AA"
        )
        parkingLotSystem.parkVehicle(
            VehicleDetails(
                "MH-65-KS-8754", DriverType.NORMAL_DRIVER,
                VehicleColor.WHITE, CarCompany.BMW
            ),
            "AA"
        )
        parkingLotSystem.parkVehicle(
            VehicleDetails(
                "MH-65-KS-8654", DriverType.NORMAL_DRIVER,
                VehicleColor.BLUE, CarCompany.NISSAN
            ),
            "AA"
        )
        val carCount = parkingLotSystem.getCountForOneBrandCar(CarCompany.BMW)
        assertEquals(2, carCount)
    }

    @Test
    fun givenVehiclesParked_WhenFindVehicleParkedInLast30Minutes_ShouldReturnVehicleSlotNumber() {
        val parkingLotSystem = ParkingLotSystem(3, 3)

        parkingLotSystem.parkVehicle(
            VehicleDetails(
                "MH-65-KS-8765", DriverType.NORMAL_DRIVER,
                VehicleColor.NO_COLOR, CarCompany.NISSAN
            ),
            "AA"
        )
        parkingLotSystem.parkVehicle(
            VehicleDetails(
                "MH-68-KS-8776", DriverType.NORMAL_DRIVER,
                VehicleColor.BLUE, CarCompany.BMW
            ),
            "AA"
        )
        val vehicleDetail = parkingLotSystem.getVehicleDetailOfGivenTime(30)
        assertEquals(Arrays.asList("Lot0 Slot0", "Lot1 Slot0"), vehicleDetail)
    }

    @Test
    fun givenParkingLots_WhenFindVehiclesAccordinglySmallVehicleAndHandicapDriverType_ShouldReturnVehicleDetails() {
        val parkingLotSystem = ParkingLotSystem(3, 3)

        parkingLotSystem.parkVehicle(
            VehicleDetails(
                "MH-65-KS-8765", DriverType.HANDICAP_DRIVER,
                Car.SMALL_CAR
            ),
            "AA"
        )
        parkingLotSystem.parkVehicle(
            VehicleDetails(
                "MH-75-KS-7338", DriverType.NORMAL_DRIVER,
                Car.SMALL_CAR
            ),
            "AA"
        )
        val vehicleDetail = parkingLotSystem.getVehicleDetailOfGivenDriverTypeAndCarSize(
            DriverType.HANDICAP_DRIVER,
            Car.SMALL_CAR,
            0
        )
        assertEquals(listOf("Lot0 Slot0 MH-65-KS-8765 HANDICAP_DRIVER SMALL_CAR"), vehicleDetail)
    }

    @Test
    fun givenParingLot_WhenHaveParkedCars_ShouldReturnTotalVehicle() {
        val parkingLotSystem = ParkingLotSystem(3, 3)

        parkingLotSystem.parkVehicle(
            VehicleDetails(
                "MH-65-KS-8765", DriverType.HANDICAP_DRIVER,
                Car.SMALL_CAR
            ),
            "AA"
        )
        parkingLotSystem.parkVehicle(
            VehicleDetails(
                "MH-85-KS-7638", DriverType.NORMAL_DRIVER,
                Car.SMALL_CAR
            ),
            "AA"
        )
        parkingLotSystem.parkVehicle(
            VehicleDetails(
                "MH-75-KS-7658", DriverType.HANDICAP_DRIVER,
                Car.LARGE_CAR
            ),
            "AA"
        )
        parkingLotSystem.parkVehicle(
            VehicleDetails(
                "MH-65-KS-7438", DriverType.NORMAL_DRIVER,
                Car.SMALL_CAR
            ),
            "AA"
        )
        val carCount = parkingLotSystem.carCount()
        assertEquals(4, carCount)
    }
}
