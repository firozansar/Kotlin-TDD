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

    fun getColor(): VehicleColor? {
        return color
    }

    private var color: VehicleColor? = null

    constructor(vehicle: String, driverType: DriverType, car: Car?) {
        this.vehicle = vehicle
        this.driverType = driverType
        this.car = car
    }

    constructor(vehicle: String, driverType: DriverType, car: Car?, color: VehicleColor?) {
        this.vehicle = vehicle
        this.driverType = driverType
        this.car = car
        this.color = color
    }

    constructor(vehicle: String, driverType: DriverType, color: VehicleColor?, carCompany: CarCompany?) {
        this.vehicle = vehicle
        this.driverType = driverType
        this.color = color
        this.carCompany = carCompany
    }

    fun getDriverType(): DriverType {
        return driverType
    }

    val vehicleSize: info.firozansari.parking.enums.Car?
        get() = car

    fun getCarCompany(): CarCompany? {
        return carCompany
    }

    val carColor: VehicleColor?
        get() = color
}