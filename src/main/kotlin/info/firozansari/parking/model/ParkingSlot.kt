package info.firozansari.parking.model

import java.time.LocalTime

class ParkingSlot(vehicleDetails: VehicleDetails, time: LocalTime, attendantName: String) {
    var vehicle: VehicleDetails
    var time: LocalTime
    var attendantName: String
    val vehicleDetails: info.firozansari.parking.model.VehicleDetails
        get() = vehicle

    override fun equals(o: Any?): Boolean {
        if (this === o) return true
        if (o == null || javaClass != o.javaClass) return false
        val that = o as ParkingSlot
        return vehicle == that.vehicle &&
                time == that.time
    }

    override fun toString(): String {
        return "ParkingSlot{" +
                "vehicle='" + vehicle + '\'' +
                ", time=" + time +
                '}'
    }

    init {
        vehicle = vehicleDetails
        this.time = time
        this.attendantName = attendantName
    }
}