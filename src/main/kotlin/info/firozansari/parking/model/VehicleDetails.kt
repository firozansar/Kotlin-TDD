package info.firozansari.parking.model

import info.firozansari.parking.enums.Car
import info.firozansari.parking.enums.CarCompany
import info.firozansari.parking.enums.DriverType
import info.firozansari.parking.enums.VehicleColor

class VehicleDetails {
    val vehicle: String
    private val driverType: DriverType
    private var car: Car? = null
    private var carCompany: CarCompany? = null
    var color: VehicleColor = VehicleColor.NO_COLOR

    constructor(vehicle: String, driverType: DriverType, car: Car?) {
        this.vehicle = vehicle
        this.driverType = driverType
        this.car = car
    }

    constructor(vehicle: String, driverType: DriverType, car: Car?, color: VehicleColor = VehicleColor.NO_COLOR) {
        this.vehicle = vehicle
        this.driverType = driverType
        this.car = car
        this.color = color
    }

    constructor(vehicle: String, driverType: DriverType, color: VehicleColor = VehicleColor.NO_COLOR, carCompany: CarCompany?) {
        this.vehicle = vehicle
        this.driverType = driverType
        this.color = color
        this.carCompany = carCompany
    }

    fun getDriverType(): DriverType {
        return driverType
    }

    val vehicleSize: Car?
        get() = car

    fun getCarCompany(): CarCompany? {
        return carCompany
    }

//    val carColor: VehicleColor
//        get() = color
}