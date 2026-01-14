package info.firozansari.parking.services

import info.firozansari.parking.enums.Car
import info.firozansari.parking.enums.CarCompany
import info.firozansari.parking.enums.DriverType
import info.firozansari.parking.enums.VehicleColor
import info.firozansari.parking.exception.ParkingLotException
import info.firozansari.parking.model.ParkingSlot
import info.firozansari.parking.model.VehicleDetails
import info.firozansari.parking.observer.ParkingLotObserver
import java.time.Duration
import java.time.LocalDateTime
import java.time.LocalTime
import java.util.stream.IntStream

class ParkingLotSystem(var lotSize: Int, numberOfLots: Int) {
    private val observers = mutableListOf<ParkingLotObserver>()
    private var parkingLots = mutableListOf<ParkingLot>()
    var numberOfLots: Int

    fun register(parkingObservers: ParkingLotObserver) {
        observers.add(parkingObservers)
    }

    @Throws(ParkingLotException::class)
    fun parkVehicle(vehicleDetails: VehicleDetails, attendantName: String?) {
        if (isVehicleParked(vehicleDetails.vehicle)) throw ParkingLotException(
            ParkingLotException.ExceptionType.ALREADY_PARKED,
            "Vehicle already parked"
        )
        if (isAnySlotFull) {
            for (observer in observers) {
                observer.capacityIsFull()
            }
            throw ParkingLotException(ParkingLotException.ExceptionType.PARKING_LOT_FULL, "Parking Lot Is Full")
        }
        val parkingSlot = attendantName?.let { ParkingSlot(vehicleDetails, LocalTime.now().withNano(0), it) }
        val parkingLot: ParkingLot = getParkingLotAccordingToDriverTypeAndCarSize(vehicleDetails)
        getEmptySlots(parkingLot)?.let { parkingLot.list.set(it, parkingSlot) }
    }

    private fun getParkingLotAccordingToDriverTypeAndCarSize(vehicleDetails: VehicleDetails): ParkingLot {
        if (vehicleDetails.vehicleSize === Car.LARGE_CAR) return getParkingLotAccordingToLargeCar(vehicleDetails.getDriverType())
        if (vehicleDetails.getDriverType() === DriverType.HANDICAP_DRIVER) for (parkingLot in parkingLots) {
            for (slot in parkingLot.list) if (slot == null) {
                return parkingLot
            }
        }
        var minimumIndex = 0
        var minimumSize: Int = parkingLots[0].getParkingSlotList()
        for (slot in parkingLots.indices) if (parkingLots[slot].getParkingSlotList() < minimumSize) {
            minimumIndex = slot
            minimumSize = parkingLots[slot].getParkingSlotList()
        }
        return parkingLots[minimumIndex]
    }

    fun getParkingLotAccordingToLargeCar(driverType: DriverType): ParkingLot {
        if (driverType === DriverType.HANDICAP_DRIVER) for (parkingLot in parkingLots) {
            val list: List<ParkingSlot?> = parkingLot.list
            for (i in 0 until list.size - 1) {
                val slot1: ParkingSlot? = list[i + 1]
                val slot: ParkingSlot? = list[i]
                if (slot == null && slot1 == null) {
                    return parkingLot
                }
            }
        }
        var minimumIndex = 0
        var minimumSize: Int = parkingLots[0].getParkingSlotList()
        for (slot in parkingLots.indices) if (parkingLots[slot].getParkingSlotList() < minimumSize) {
            minimumIndex = slot
            minimumSize = parkingLots[slot].getParkingSlotList()
        }
        return parkingLots[minimumIndex]
    }

    @Throws(ParkingLotException::class)
    fun getParkTime(vehicle: String?): LocalTime {
        for (parkingLot in parkingLots) {
            for (slot in parkingLot.list) if (slot != null && slot.vehicleDetails.vehicle == vehicle
            ) {
                return slot.time
            }
        }
        throw ParkingLotException(ParkingLotException.ExceptionType.NOT_FOUND, "vehicle not present")
    }

    fun getEmptySlots(parkingLot: ParkingLot): Int? {
        for (i in 0 until parkingLot.list.size) {
            if (parkingLot.list[i] == null) return i
        }
        return null
    }

    private val isAnySlotFull: Boolean
        get() {
            var vehicleCount = 0
            for (parkingLot in parkingLots) {
                val vehicles: Int = parkingLot.getParkingSlotList()
                vehicleCount += vehicles
            }
            return lotSize * numberOfLots == vehicleCount
        }

    fun isVehicleParked(vehicle: String?): Boolean {
        for (parkingLot in parkingLots) {
            for (slot in parkingLot.list) if (slot != null && slot.vehicleDetails.vehicle == vehicle) return true
        }
        return false
    }

    fun unPark(vehicle: String?): Boolean {
        if (vehicle == null) return false
        for (parkingLot in parkingLots) for (slot in parkingLot.list) {
            if (slot != null && slot.vehicleDetails.vehicle == vehicle) {
                parkingLot.list[parkingLot.list.indexOf(slot)] = null
                for (observer in observers) {
                    observer.capacityIsAvailable()
                }
                return true
            }
        }
        return false
    }

    @Throws(ParkingLotException::class)
    fun findVehicleLocation(vehicle: String?): Int {
        for (parkingLot in parkingLots) for (slot in parkingLot.list) {
            if (slot != null && slot.vehicleDetails.vehicle == vehicle) {
                return parkingLot.list.indexOf(slot)
            }
        }
        throw ParkingLotException(ParkingLotException.ExceptionType.NOT_FOUND, " vehicle not found")
    }

    fun vehiclePosition(vehicle: String?): String? {
        var lot = 0
        for (parkingLot in parkingLots) {
            for (slot in parkingLot.list) {
                if (slot != null && slot.vehicleDetails.vehicle == vehicle) {
                    val slot1: Int = parkingLot.list.indexOf(slot)
                    return "Lot$lot Slot$slot1"
                }
            }
            lot++
        }
        return null
    }

    fun getLocationOfWhiteVehicle(vehicleColor: VehicleColor?): List<String> {
        val whiteColorVehicleLocation: MutableList<String> = ArrayList()
        for ((lot, parkingLot) in parkingLots.withIndex()) {
            for (slot in parkingLot.list) {
                if (slot != null && slot.vehicleDetails.color == vehicleColor) {
                    val slot1: Int = parkingLot.list.indexOf(slot)
                    val location = "Lot$lot Slot$slot1"
                    whiteColorVehicleLocation.add(location)
                }
            }
        }
        return whiteColorVehicleLocation
    }

    fun getLocationOfVehicleByGivingColorAndBrand(vehicleColor: VehicleColor?, carCompany: CarCompany?): List<String> {
        val VehicleInformation: MutableList<String> = ArrayList()
        var lot = 0
        for (parkingLot in parkingLots) {
            for (slot in parkingLot.list) {
                if (slot != null && slot.vehicleDetails.color == vehicleColor &&
                    slot.vehicleDetails.getCarCompany()?.equals(carCompany) == true
                ) {
                    val slot1: Int = parkingLot.list.indexOf(slot)
                    val information = ("Lot" + lot + " " + "Slot" + slot1 + " " + slot.vehicleDetails.vehicle
                            + " " + slot.attendantName)
                    VehicleInformation.add(information)
                }
            }
            lot++
        }
        return VehicleInformation
    }

    fun getCountForOneBrandCar(carCompany: CarCompany?): Int {
        var lot = 0
        for (parkingLot in parkingLots) {
            for (slot in parkingLot.list) {
                if (slot != null && slot.vehicleDetails.getCarCompany()?.equals(carCompany) == true) {
                    lot++
                }
            }
        }
        return lot
    }

    fun getVehicleDetailOfGivenTime(minutes: Int): List<String> {
        val vehicleInformation: MutableList<String> = ArrayList()
        for ((lot, parkingLot) in parkingLots.withIndex()) {
            for (slot in parkingLot.list) {
                if (slot != null && Duration.between(slot.time, LocalDateTime.now()).toMinutes() <= minutes) {
                    val slot1: Int = parkingLot.list.indexOf(slot)
                    val location = "Lot$lot Slot$slot1"
                    vehicleInformation.add(location)
                }
            }
        }
        return vehicleInformation
    }

    fun getVehicleDetailOfGivenDriverTypeAndCarSize(driverType: DriverType?, carSize: Car?, lot: Int): List<String> {
        val vehicleInformation: MutableList<String> = ArrayList()
        var lot1 = lot
        val lot2 = 0
        for (parkingLot in parkingLots) {
            for (slot in parkingLot.list) {
                if (slot != null && slot.vehicleDetails.getDriverType() == driverType &&
                    slot.vehicleDetails.vehicleSize?.equals(carSize) == true && lot2 == lot1
                ) {
                    val slot1: Int = parkingLot.list.indexOf(slot)
                    val location = ("Lot" + lot1 + " " + "Slot" + slot1 + " " + slot.vehicleDetails.vehicle
                            + " " + slot.vehicleDetails.getDriverType() + " "
                            + slot.vehicleDetails.vehicleSize)
                    vehicleInformation.add(location)
                }
            }
            lot1++
        }
        return vehicleInformation
    }

    fun carCount(): Int {
        var lot = 0
        for (parkingLot in parkingLots) {
            for (slot in parkingLot.list) {
                if (slot != null) lot++
            }
        }
        return lot
    }

    init {
        parkingLots = ArrayList()
        IntStream.range(0, numberOfLots).forEachOrdered { slots: Int ->
            parkingLots.add(
                slots, ParkingLot(
                    lotSize
                )
            )
        }
        this.numberOfLots = numberOfLots
    }
}
