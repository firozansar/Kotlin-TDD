package info.firozansari.parking.observer

class AirportSecurity : ParkingLotObserver {
    var isCapacityFull = false
        private set

    override fun capacityIsFull() {
        isCapacityFull = true
    }

    override fun capacityIsAvailable() {
        isCapacityFull = false
    }
}