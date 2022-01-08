package info.firozansari.parking.observer

class ParkingLotOwner : ParkingLotObserver {
    var isCapacityFull = false
        private set

    override fun capacityIsFull() {
        isCapacityFull = true
    }

    override fun capacityIsAvailable() {
        isCapacityFull = false
    }
}