package info.firozansari.parking.services

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import info.firozansari.parking.model.VehicleDetails
import info.firozansari.parking.enums.DriverType
import info.firozansari.parking.enums.Car
import org.junit.jupiter.api.Assertions
import info.firozansari.parking.exception.ParkingLotException
import info.firozansari.parking.observer.AirportSecurity
import info.firozansari.parking.observer.ParkingLotOwner
import java.time.LocalTime
import info.firozansari.parking.enums.VehicleColor
import java.util.Arrays
import info.firozansari.parking.enums.CarCompany

class ParkingLotSystemTest {
    var parkingLotSystem: ParkingLotSystem? = null
    @BeforeEach
    fun setUp() {
        parkingLotSystem = ParkingLotSystem(3, 1)
    }

    //UC1 Park the vehicle
    @Test
    fun givenVehicle_WhenParked_ShouldReturnTrue() {
        try {
            parkingLotSystem!!.parkVehicle(
                VehicleDetails(
                    "vehicle", DriverType.NORMAL_DRIVER,
                    Car.SMALL_CAR
                ), "AA"
            )
            val isParked = parkingLotSystem!!.isVehicleParked("vehicle")
            Assertions.assertTrue(isParked)
        } catch (e: ParkingLotException) {
            e.printStackTrace()
        }
    }

    @Test
    fun givenVehicleParked_WhenAlreadyParked_ShouldThrowException() {
        try {
            parkingLotSystem!!.parkVehicle(
                VehicleDetails(
                    "vehicle", DriverType.NORMAL_DRIVER,
                    Car.SMALL_CAR
                ), "AA"
            )
            parkingLotSystem!!.parkVehicle(
                VehicleDetails(
                    "vehicle", DriverType.NORMAL_DRIVER,
                    Car.SMALL_CAR
                ), "AA"
            )
        } catch (e: ParkingLotException) {
            println(e.message)
            Assertions.assertEquals(ParkingLotException.ExceptionType.ALREADY_PARKED, e.type)
        }
    }

    //UC2 unPark the vehicle
    @Test
    fun givenVehicle_WhenUnParked_ShouldReturnTrue() {
        try {
            parkingLotSystem!!.parkVehicle(
                VehicleDetails(
                    "vehicle", DriverType.NORMAL_DRIVER,
                    Car.SMALL_CAR
                ), "AA"
            )
            val isUnParked = parkingLotSystem!!.unPark("vehicle")
            Assertions.assertTrue(isUnParked)
        } catch (ignored: ParkingLotException) {
        }
    }

    @Test
    fun givenAnotherVehicle_WhenUnParked_ShouldReturnFalse() {
        try {
            parkingLotSystem!!.parkVehicle(
                VehicleDetails(
                    "vehicle", DriverType.NORMAL_DRIVER,
                    Car.SMALL_CAR
                ), "AA"
            )
            val isUnParked = parkingLotSystem!!.unPark("vehicle1")
            Assertions.assertFalse(isUnParked)
        } catch (e: ParkingLotException) {
            e.printStackTrace()
        }
    }

    //UC3
    @Test
    fun givenVehicleParked_WhenLotFull_ShouldThrowException() {
        try {
            parkingLotSystem!!.parkVehicle(
                VehicleDetails(
                    "vehicle1", DriverType.NORMAL_DRIVER,
                    Car.SMALL_CAR
                ), "AA"
            )
            parkingLotSystem!!.parkVehicle(
                VehicleDetails(
                    "vehicle2", DriverType.NORMAL_DRIVER,
                    Car.SMALL_CAR
                ), "AA"
            )
            parkingLotSystem!!.parkVehicle(
                VehicleDetails(
                    "vehicle3", DriverType.NORMAL_DRIVER,
                    Car.SMALL_CAR
                ), "AA"
            )
            parkingLotSystem!!.parkVehicle(
                VehicleDetails(
                    "vehicle4", DriverType.NORMAL_DRIVER,
                    Car.SMALL_CAR
                ), "AA"
            )
        } catch (e: ParkingLotException) {
            println(e.message)
            Assertions.assertEquals(ParkingLotException.ExceptionType.PARKING_LOT_FULL, e.type)
        }
    }

    //UC4
    @Test
    fun givenParkingLot_WhenFull_ShouldRedirectSecurityStaff() {
        val airportSecurity = AirportSecurity()
        parkingLotSystem!!.register(airportSecurity)
        try {
            parkingLotSystem!!.parkVehicle(
                VehicleDetails(
                    "vehicle1", DriverType.NORMAL_DRIVER,
                    Car.SMALL_CAR
                ), "AA"
            )
            parkingLotSystem!!.parkVehicle(
                VehicleDetails(
                    "vehicle2", DriverType.NORMAL_DRIVER,
                    Car.SMALL_CAR
                ), "AA"
            )
            parkingLotSystem!!.parkVehicle(
                VehicleDetails(
                    "vehicle3", DriverType.NORMAL_DRIVER,
                    Car.SMALL_CAR
                ), "AA"
            )
            parkingLotSystem!!.parkVehicle(
                VehicleDetails(
                    "vehicle4", DriverType.NORMAL_DRIVER,
                    Car.SMALL_CAR
                ), "AA"
            )
        } catch (e: ParkingLotException) {
            val capacityFull = airportSecurity.isCapacityFull
            Assertions.assertTrue(capacityFull)
        }
    }

    //UC4
    @Test
    fun givenParkingLot_WhenNotFull_ShouldNotBeRedirectSecurityStaff() {
        val airportSecurity = AirportSecurity()
        parkingLotSystem!!.register(airportSecurity)
        try {
            parkingLotSystem!!.parkVehicle(
                VehicleDetails(
                    "vehicle1", DriverType.NORMAL_DRIVER,
                    Car.SMALL_CAR
                ), "AA"
            )
            parkingLotSystem!!.parkVehicle(
                VehicleDetails(
                    "vehicle2", DriverType.NORMAL_DRIVER,
                    Car.SMALL_CAR
                ), "AA"
            )
            parkingLotSystem!!.parkVehicle(
                VehicleDetails(
                    "vehicle3", DriverType.NORMAL_DRIVER,
                    Car.SMALL_CAR
                ), "AA"
            )
            parkingLotSystem!!.parkVehicle(
                VehicleDetails(
                    "vehicle4", DriverType.NORMAL_DRIVER,
                    Car.SMALL_CAR
                ), "AA"
            )
        } catch (e: ParkingLotException) {
            parkingLotSystem!!.unPark("vehicle3")
            val capacityFull = airportSecurity.isCapacityFull
            Assertions.assertFalse(capacityFull)
        }
    }

    @Test
    fun givenWhenLotIsFull_ShouldInformOwner() {
        val parkingLotOwner = ParkingLotOwner()
        parkingLotSystem!!.register(parkingLotOwner)
        try {
            parkingLotSystem!!.parkVehicle(
                VehicleDetails(
                    "vehicle1", DriverType.NORMAL_DRIVER,
                    Car.SMALL_CAR
                ), "AA"
            )
            parkingLotSystem!!.parkVehicle(
                VehicleDetails(
                    "vehicle2", DriverType.NORMAL_DRIVER,
                    Car.SMALL_CAR
                ), "AA"
            )
            parkingLotSystem!!.parkVehicle(
                VehicleDetails(
                    "vehicle3", DriverType.NORMAL_DRIVER,
                    Car.SMALL_CAR
                ), "AA"
            )
            parkingLotSystem!!.parkVehicle(
                VehicleDetails(
                    "vehicle4", DriverType.NORMAL_DRIVER,
                    Car.SMALL_CAR
                ), "AA"
            )
        } catch (e: ParkingLotException) {
            val capacityFull = parkingLotOwner.isCapacityFull
            Assertions.assertTrue(capacityFull)
        }
    }

    @Test
    fun givenWhenLotIsEmpty_ShouldInformOwner() {
        val parkingLotOwner = ParkingLotOwner()
        try {
            parkingLotSystem!!.register(parkingLotOwner)
            parkingLotSystem!!.parkVehicle(
                VehicleDetails(
                    "vehicle1", DriverType.NORMAL_DRIVER,
                    Car.SMALL_CAR
                ), "AA"
            )
            parkingLotSystem!!.parkVehicle(
                VehicleDetails(
                    "vehicle2", DriverType.NORMAL_DRIVER,
                    Car.SMALL_CAR
                ), "AA"
            )
            parkingLotSystem!!.parkVehicle(
                VehicleDetails(
                    "vehicle3", DriverType.NORMAL_DRIVER,
                    Car.SMALL_CAR
                ), "AA"
            )
            parkingLotSystem!!.parkVehicle(
                VehicleDetails(
                    "vehicle4", DriverType.NORMAL_DRIVER,
                    Car.SMALL_CAR
                ), "AA"
            )
        } catch (e: ParkingLotException) {
            parkingLotSystem!!.unPark("vehicle2")
            val capacityFull = parkingLotOwner.isCapacityFull
            Assertions.assertFalse(capacityFull)
        }
    }

    //UC6
    @Test
    fun givenVehicle_ShouldPark_OnAvailableSlot() {
        try {
            parkingLotSystem!!.parkVehicle(
                VehicleDetails(
                    "vehicle2", DriverType.NORMAL_DRIVER,
                    Car.SMALL_CAR
                ), "AA"
            )
            parkingLotSystem!!.parkVehicle(
                VehicleDetails(
                    "vehicle3", DriverType.NORMAL_DRIVER,
                    Car.SMALL_CAR
                ), "AA"
            )
        } catch (e: ParkingLotException) {
            e.printStackTrace()
        }
    }

    //UC7
    @Test
    fun givenVehicle_WhenParkedShouldFindTheLocation() {
        try {
            parkingLotSystem!!.parkVehicle(
                VehicleDetails(
                    "vehicle", DriverType.NORMAL_DRIVER,
                    Car.SMALL_CAR
                ), "AA"
            )
            val vehicleLocation = parkingLotSystem!!.findVehicleLocation("vehicle")
            Assertions.assertEquals(0, vehicleLocation)
        } catch (e: ParkingLotException) {
            e.printStackTrace()
        }
    }

    @Test
    fun givenVehicle_WhenNotFound_ShouldThrowException() {
        try {
            parkingLotSystem!!.findVehicleLocation("vehicle2")
        } catch (e: ParkingLotException) {
            println(e.message)
            Assertions.assertEquals(ParkingLotException.ExceptionType.NOT_FOUND, e.type)
        }
    }

    //UC8
    @Test
    fun givenVehicle_WhenParked_ShouldReturnTime() {
        try {
            parkingLotSystem!!.parkVehicle(
                VehicleDetails(
                    "vehicle", DriverType.NORMAL_DRIVER,
                    Car.SMALL_CAR
                ), "AA"
            )
            val parkTime = parkingLotSystem!!.getParkTime("vehicle")
            Assertions.assertEquals(parkTime, LocalTime.now().withNano(0))
        } catch (e: ParkingLotException) {
            println(e.message)
        }
    }

    @Test
    fun givenVehicle_WhenNotParked_ShouldThrowException() {
        try {
            parkingLotSystem!!.parkVehicle(
                VehicleDetails(
                    "vehicle1", DriverType.NORMAL_DRIVER,
                    Car.SMALL_CAR
                ), "AA"
            )
            val parkTime = parkingLotSystem!!.getParkTime("vehicle2")
            Assertions.assertEquals(parkTime, LocalTime.now().withNano(0))
        } catch (e: ParkingLotException) {
            Assertions.assertEquals(ParkingLotException.ExceptionType.NOT_FOUND, e.type)
        }
    }

    //UC9
    @Test
    fun givenVehicle_WhenParkedAndUnParkedInLot_ShouldEvenlyDistributed() {
        val parkingLotSystem = ParkingLotSystem(3, 3)
        try {
            parkingLotSystem.parkVehicle(
                VehicleDetails(
                    "vehicle1", DriverType.NORMAL_DRIVER,
                    Car.SMALL_CAR
                ), "AA"
            )
            parkingLotSystem.parkVehicle(
                VehicleDetails(
                    "vehicle2", DriverType.NORMAL_DRIVER,
                    Car.SMALL_CAR
                ), "AA"
            )
            parkingLotSystem.parkVehicle(
                VehicleDetails(
                    "vehicle3", DriverType.NORMAL_DRIVER,
                    Car.SMALL_CAR
                ), "AA"
            )
            parkingLotSystem.parkVehicle(
                VehicleDetails(
                    "vehicle4", DriverType.NORMAL_DRIVER,
                    Car.SMALL_CAR
                ), "AA"
            )
            parkingLotSystem.parkVehicle(
                VehicleDetails(
                    "vehicle5", DriverType.NORMAL_DRIVER,
                    Car.SMALL_CAR
                ), "AA"
            )
            parkingLotSystem.parkVehicle(
                VehicleDetails(
                    "vehicle6", DriverType.NORMAL_DRIVER,
                    Car.SMALL_CAR
                ), "AA"
            )
            parkingLotSystem.parkVehicle(
                VehicleDetails(
                    "vehicle7", DriverType.NORMAL_DRIVER,
                    Car.SMALL_CAR
                ), "AA"
            )
            parkingLotSystem.unPark("vehicle7")
            val position = parkingLotSystem.vehiclePosition("vehicle3")
            Assertions.assertEquals("Lot2 Slot0", position)
        } catch (e: ParkingLotException) {
            println(e.message)
        }
    }

    //UC10
    @Test
    fun givenVehicleToPark_WhenDriverIsHandicap_ShouldParkVehicleAtNearestSpot() {
        val parkingLotSystem = ParkingLotSystem(3, 3)
        try {
            parkingLotSystem.parkVehicle(
                VehicleDetails(
                    "vehicle1", DriverType.NORMAL_DRIVER,
                    Car.SMALL_CAR
                ), "AA"
            )
            parkingLotSystem.parkVehicle(
                VehicleDetails(
                    "vehicle2", DriverType.NORMAL_DRIVER,
                    Car.SMALL_CAR
                ), "AA"
            )
            parkingLotSystem.parkVehicle(
                VehicleDetails(
                    "vehicle3", DriverType.NORMAL_DRIVER,
                    Car.SMALL_CAR
                ), "AA"
            )
            parkingLotSystem.unPark("vehicle2")
            parkingLotSystem.parkVehicle(
                VehicleDetails(
                    "vehicle4", DriverType.HANDICAP_DRIVER,
                    Car.SMALL_CAR
                ), "AA"
            )
            parkingLotSystem.parkVehicle(
                VehicleDetails(
                    "vehicle5", DriverType.HANDICAP_DRIVER,
                    Car.SMALL_CAR
                ), "AA"
            )
            parkingLotSystem.parkVehicle(
                VehicleDetails(
                    "vehicle6", DriverType.NORMAL_DRIVER,
                    Car.SMALL_CAR
                ), "AA"
            )
            parkingLotSystem.parkVehicle(
                VehicleDetails(
                    "vehicle7", DriverType.NORMAL_DRIVER,
                    Car.SMALL_CAR
                ), "AA"
            )
            parkingLotSystem.parkVehicle(
                VehicleDetails(
                    "vehicle8", DriverType.NORMAL_DRIVER,
                    Car.SMALL_CAR
                ), "AA"
            )
            parkingLotSystem.parkVehicle(
                VehicleDetails(
                    "vehicle9", DriverType.NORMAL_DRIVER,
                    Car.SMALL_CAR
                ), "AA"
            )
            parkingLotSystem.unPark("vehicle7")
            parkingLotSystem.parkVehicle(
                VehicleDetails(
                    "vehicleH", DriverType.HANDICAP_DRIVER,
                    Car.SMALL_CAR
                ), "AA"
            )
            val position = parkingLotSystem.vehiclePosition("vehicle4")
            Assertions.assertEquals("Lot0 Slot1", position)
        } catch (e: ParkingLotException) {
            println(e.message)
        }
    }

    //UC11
    @Test
    fun givenVehicleToPark_WhenDriverIsHandicap_AndCarIsLarge_ShouldReturnExpectedSlotNumbers() {
        val parkingLotSystem = ParkingLotSystem(3, 3)
        try {
            parkingLotSystem.parkVehicle(
                VehicleDetails(
                    "vehicle1", DriverType.NORMAL_DRIVER,
                    Car.SMALL_CAR
                ), "AA"
            )
            parkingLotSystem.parkVehicle(
                VehicleDetails(
                    "vehicle2", DriverType.NORMAL_DRIVER,
                    Car.SMALL_CAR
                ), "AA"
            )
            parkingLotSystem.parkVehicle(
                VehicleDetails(
                    "vehicle3", DriverType.NORMAL_DRIVER,
                    Car.SMALL_CAR
                ), "AA"
            )
            parkingLotSystem.parkVehicle(
                VehicleDetails(
                    "vehicle4", DriverType.HANDICAP_DRIVER,
                    Car.SMALL_CAR
                ), "AA"
            )
            parkingLotSystem.parkVehicle(
                VehicleDetails(
                    "vehicle5", DriverType.HANDICAP_DRIVER,
                    Car.LARGE_CAR
                ), "AA"
            )
            val position = parkingLotSystem.vehiclePosition("vehicle5")
            Assertions.assertEquals("Lot1 Slot1", position)
        } catch (e: ParkingLotException) {
            println(e.message)
        }
    }

    @Test
    fun givenVehicleToPark_WhenDriverIsHandicap_AndCarIsMedium_ShouldReturnExpectedSlotNumbers() {
        val parkingLotSystem = ParkingLotSystem(3, 3)
        try {
            parkingLotSystem.parkVehicle(
                VehicleDetails(
                    "vehicle1", DriverType.NORMAL_DRIVER,
                    Car.SMALL_CAR
                ), "AA"
            )
            parkingLotSystem.parkVehicle(
                VehicleDetails(
                    "vehicle2", DriverType.NORMAL_DRIVER,
                    Car.SMALL_CAR
                ), "AA"
            )
            parkingLotSystem.parkVehicle(
                VehicleDetails(
                    "vehicle3", DriverType.NORMAL_DRIVER,
                    Car.SMALL_CAR
                ), "AA"
            )
            parkingLotSystem.parkVehicle(
                VehicleDetails(
                    "vehicle4", DriverType.NORMAL_DRIVER,
                    Car.SMALL_CAR
                ), "AA"
            )
            parkingLotSystem.parkVehicle(
                VehicleDetails(
                    "vehicle5", DriverType.HANDICAP_DRIVER,
                    Car.LARGE_CAR
                ), "AA"
            )
            parkingLotSystem.parkVehicle(
                VehicleDetails(
                    "vehicle6", DriverType.HANDICAP_DRIVER,
                    Car.SMALL_CAR
                ), "AA"
            )
            val position = parkingLotSystem.vehiclePosition("vehicle6")
            Assertions.assertEquals("Lot0 Slot2", position)
        } catch (e: ParkingLotException) {
            println(e.message)
        }
    }

    @Test
    fun givenVehicleToPark_WhenDriverIsNormal_AndCarIsLarge_ShouldReturnExpectedSlotNumbers() {
        val parkingLotSystem = ParkingLotSystem(3, 3)
        try {
            parkingLotSystem.parkVehicle(
                VehicleDetails(
                    "vehicle1", DriverType.NORMAL_DRIVER,
                    Car.SMALL_CAR
                ), "AA"
            )
            parkingLotSystem.parkVehicle(
                VehicleDetails(
                    "vehicle2", DriverType.NORMAL_DRIVER,
                    Car.SMALL_CAR
                ), "AA"
            )
            parkingLotSystem.parkVehicle(
                VehicleDetails(
                    "vehicle3", DriverType.NORMAL_DRIVER,
                    Car.SMALL_CAR
                ), "AA"
            )
            parkingLotSystem.parkVehicle(
                VehicleDetails(
                    "vehicle4", DriverType.NORMAL_DRIVER,
                    Car.SMALL_CAR
                ), "AA"
            )
            parkingLotSystem.parkVehicle(
                VehicleDetails(
                    "vehicle5", DriverType.NORMAL_DRIVER,
                    Car.LARGE_CAR
                ), "AA"
            )
            parkingLotSystem.parkVehicle(
                VehicleDetails(
                    "vehicle6", DriverType.NORMAL_DRIVER,
                    Car.SMALL_CAR
                ), "AA"
            )
            val position = parkingLotSystem.vehiclePosition("vehicle5")
            Assertions.assertEquals("Lot1 Slot1", position)
        } catch (e: ParkingLotException) {
            println(e.message)
        }
    }

    //UC12
    @Test
    fun givenVehicleToPark_WhenHaveWhiteColor_ShouldReturnExpectedSlotNumbers() {
        val parkingLotSystem = ParkingLotSystem(3, 3)
        try {
            parkingLotSystem.parkVehicle(
                VehicleDetails(
                    "vehicle1", DriverType.NORMAL_DRIVER,
                    Car.SMALL_CAR, VehicleColor.NO_COLOR
                ), "AA"
            )
            parkingLotSystem.parkVehicle(
                VehicleDetails(
                    "vehicle2", DriverType.NORMAL_DRIVER,
                    Car.SMALL_CAR, VehicleColor.WHITE
                ), "AA"
            )
            parkingLotSystem.parkVehicle(
                VehicleDetails(
                    "vehicle3", DriverType.NORMAL_DRIVER,
                    Car.SMALL_CAR, VehicleColor.NO_COLOR
                ), "AA"
            )
            parkingLotSystem.parkVehicle(
                VehicleDetails(
                    "vehicle4", DriverType.NORMAL_DRIVER,
                    Car.SMALL_CAR, VehicleColor.WHITE
                ), "AA"
            )
            val whiteColorVehicle = parkingLotSystem.getLocationOfWhiteVehicle(VehicleColor.WHITE)
            Assertions.assertEquals(Arrays.asList("Lot0 Slot1", "Lot1 Slot0"), whiteColorVehicle)
        } catch (e: ParkingLotException) {
            println(e.message)
        }
    }

    //UC13
    @Test
    fun givenToyotaCar_WhenHaveBlueColor_ShouldReturnExpectedLocation() {
        val parkingLotSystem = ParkingLotSystem(3, 3)
        try {
            parkingLotSystem.parkVehicle(
                VehicleDetails(
                    "MH-65-KS-8765", DriverType.NORMAL_DRIVER,
                    VehicleColor.NO_COLOR, CarCompany.NISSAN
                ), "AA"
            )
            parkingLotSystem.parkVehicle(
                VehicleDetails(
                    "MH-68-KS-8776", DriverType.NORMAL_DRIVER,
                    VehicleColor.BLUE, CarCompany.TOYOTA
                ), "AA"
            )
            parkingLotSystem.parkVehicle(
                VehicleDetails(
                    "MH-65-KS-8754", DriverType.NORMAL_DRIVER,
                    VehicleColor.WHITE, CarCompany.NISSAN
                ), "AA"
            )
            parkingLotSystem.parkVehicle(
                VehicleDetails(
                    "MH-65-KS-8654", DriverType.NORMAL_DRIVER,
                    VehicleColor.BLUE, CarCompany.NISSAN
                ), "AA"
            )
            val carDetail = parkingLotSystem.getLocationOfVehicleByGivingColorAndBrand(
                VehicleColor.BLUE,
                CarCompany.TOYOTA
            )
            Assertions.assertEquals(Arrays.asList("Lot1 Slot0 MH-68-KS-8776 AA"), carDetail)
        } catch (e: ParkingLotException) {
            println(e.message)
        }
    }

    //UC14
    @Test
    fun givenBMWCar_WhenParked_ShouldReturnTotalCountOFBMWCar() {
        val parkingLotSystem = ParkingLotSystem(3, 3)
        try {
            parkingLotSystem.parkVehicle(
                VehicleDetails(
                    "MH-65-KS-8765", DriverType.NORMAL_DRIVER,
                    VehicleColor.NO_COLOR, CarCompany.NISSAN
                ), "AA"
            )
            parkingLotSystem.parkVehicle(
                VehicleDetails(
                    "MH-68-KS-8776", DriverType.NORMAL_DRIVER,
                    VehicleColor.BLUE, CarCompany.BMW
                ), "AA"
            )
            parkingLotSystem.parkVehicle(
                VehicleDetails(
                    "MH-65-KS-8754", DriverType.NORMAL_DRIVER,
                    VehicleColor.WHITE, CarCompany.BMW
                ), "AA"
            )
            parkingLotSystem.parkVehicle(
                VehicleDetails(
                    "MH-65-KS-8654", DriverType.NORMAL_DRIVER,
                    VehicleColor.BLUE, CarCompany.NISSAN
                ), "AA"
            )
            val carCount = parkingLotSystem.getCountForOneBrandCar(CarCompany.BMW)
            Assertions.assertEquals(2, carCount)
        } catch (e: ParkingLotException) {
            println(e.message)
        }
    }

    //UC15
    @Test
    fun givenVehiclesParked_WhenFindVehicleParkedInLast30Minutes_ShouldReturnVehicleSlotNumber() {
        val parkingLotSystem = ParkingLotSystem(3, 3)
        try {
            parkingLotSystem.parkVehicle(
                VehicleDetails(
                    "MH-65-KS-8765", DriverType.NORMAL_DRIVER,
                    VehicleColor.NO_COLOR, CarCompany.NISSAN
                ), "AA"
            )
            parkingLotSystem.parkVehicle(
                VehicleDetails(
                    "MH-68-KS-8776", DriverType.NORMAL_DRIVER,
                    VehicleColor.BLUE, CarCompany.BMW
                ), "AA"
            )
            val vehicleDetail = parkingLotSystem.getVehicleDetailOfGivenTime(30)
            Assertions.assertEquals(Arrays.asList("Lot0 Slot0", "Lot1 Slot0"), vehicleDetail)
        } catch (e: ParkingLotException) {
            println(e.message)
        }
    }

    //UC16
    @Test
    fun givenParkingLots_WhenFindVehiclesAccordinglySmallVehicleAndHandicapDriverType_ShouldReturnVehicleDetails() {
        val parkingLotSystem = ParkingLotSystem(3, 3)
        try {
            parkingLotSystem.parkVehicle(
                VehicleDetails(
                    "MH-65-KS-8765", DriverType.HANDICAP_DRIVER,
                    Car.SMALL_CAR
                ), "AA"
            )
            parkingLotSystem.parkVehicle(
                VehicleDetails(
                    "MH-75-KS-7338", DriverType.NORMAL_DRIVER,
                    Car.SMALL_CAR
                ), "AA"
            )
            val vehicleDetail = parkingLotSystem.getVehicleDetailOfGivenDriverTypeAndCarSize(
                DriverType.HANDICAP_DRIVER,
                Car.SMALL_CAR,
                0
            )
            Assertions.assertEquals(Arrays.asList("Lot0 Slot0 MH-65-KS-8765 HANDICAP_DRIVER SMALL_CAR"), vehicleDetail)
        } catch (e: ParkingLotException) {
            println(e.message)
        }
    }

    //UC17
    @Test
    fun givenParingLot_WhenHaveParkedCars_ShouldReturnTotalVehicle() {
        val parkingLotSystem = ParkingLotSystem(3, 3)
        try {
            parkingLotSystem.parkVehicle(
                VehicleDetails(
                    "MH-65-KS-8765", DriverType.HANDICAP_DRIVER,
                    Car.SMALL_CAR
                ), "AA"
            )
            parkingLotSystem.parkVehicle(
                VehicleDetails(
                    "MH-85-KS-7638", DriverType.NORMAL_DRIVER,
                    Car.SMALL_CAR
                ), "AA"
            )
            parkingLotSystem.parkVehicle(
                VehicleDetails(
                    "MH-75-KS-7658", DriverType.HANDICAP_DRIVER,
                    Car.LARGE_CAR
                ), "AA"
            )
            parkingLotSystem.parkVehicle(
                VehicleDetails(
                    "MH-65-KS-7438", DriverType.NORMAL_DRIVER,
                    Car.SMALL_CAR
                ), "AA"
            )
            val carCount = parkingLotSystem.carCount()
            Assertions.assertEquals(4, carCount)
        } catch (e: ParkingLotException) {
            println(e.message)
        }
    }
}