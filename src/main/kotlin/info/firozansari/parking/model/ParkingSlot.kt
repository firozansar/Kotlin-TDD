package info.firozansari.parking.model

import java.time.LocalTime

class ParkingSlot(vehicleDetails: VehicleDetails, time: LocalTime, attendantName: String) {
    var vehicle: VehicleDetails
    var time: LocalTime
    var attendantName: String
    val vehicleDetails: VehicleDetails
        get() = vehicle

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || javaClass != other.javaClass) return false
        val that = other as ParkingSlot
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