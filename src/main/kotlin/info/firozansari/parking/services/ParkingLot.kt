package info.firozansari.parking.services

import info.firozansari.parking.model.ParkingSlot

class ParkingLot(var lotSize: Int) {

    private val parkingSlotList: MutableList<ParkingSlot?> = ArrayList()

    private fun initializeParkingLot() {
        val bound = lotSize
        for (slots in 0 until bound) {
            val parkingSlot: ParkingSlot? = null
            parkingSlotList.add(parkingSlot)
        }
    }

    fun getParkingSlotList(): Int {
        var numberOFVehicle = 0
        for (i in 0 until lotSize) {
            if (parkingSlotList[i] != null) numberOFVehicle++
        }
        return numberOFVehicle
    }

    val list: MutableList<ParkingSlot?>
        get() = parkingSlotList

    init {
        initializeParkingLot()
    }
}