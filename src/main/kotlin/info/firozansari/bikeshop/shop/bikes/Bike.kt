package info.firozansari.bikeshop.shop.bikes

import info.firozansari.bikeshop.behaviours.Cycle
import info.firozansari.bikeshop.shop.Product
import info.firozansari.bikeshop.shop.components.Crankset
import info.firozansari.bikeshop.shop.components.Frame
import info.firozansari.bikeshop.shop.components.Tyre
import info.firozansari.bikeshop.shop.components.Wheel

abstract class Bike(val frame: Frame, crankset: Crankset, price: Int, type: String) : Product(price), Cycle {
    var tyres: ArrayList<Tyre> = ArrayList()
    var wheels: ArrayList<Wheel>
    private val crankset: Crankset
    private val type: String
    private fun wheelsCount(): Int {
        return wheels.size
    }

    fun addWheel(wheel: Wheel) {
        if (wheelsCount() < 4) {
            wheels.add(wheel)
        }
    }

    fun tyresCount(): Int {
        return tyres.size
    }

    fun addTyre(tyre: Tyre) {
        if (tyresCount() < 4) {
            tyres.add(tyre)
        }
    }

    override var price: Int
        get() {
            var total = 0
            for (wheel in wheels) {
                total += wheel.price
            }
            for (tyre in tyres) {
                total += tyre.price
            }
            total += frame.price
            total += crankset.price
            return total.also { this.price = it }
        }
        set(price) {
            super.price = price
        }

    override fun cycle(): String {
        return "I cycle on $type bike, I am free!"
    }

    init {
        this.crankset = crankset
        wheels = ArrayList()
        this.type = type
    }
}