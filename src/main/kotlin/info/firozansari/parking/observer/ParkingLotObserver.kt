package info.firozansari.parking.observer

interface ParkingLotObserver {
    fun capacityIsFull()
    fun capacityIsAvailable()
}