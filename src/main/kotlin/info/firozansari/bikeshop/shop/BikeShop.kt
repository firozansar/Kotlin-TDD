package info.firozansari.bikeshop.shop

import info.firozansari.bikeshop.behaviours.Buy
import info.firozansari.bikeshop.behaviours.Sell
import info.firozansari.bikeshop.people.Customer
import info.firozansari.bikeshop.people.Staff
import info.firozansari.bikeshop.shop.bikes.Bike
import info.firozansari.bikeshop.shop.components.BikePart

class BikeShop(val staff: Staff, val customer: Customer, var till: Int) : Buy, Sell {
    var bikes: ArrayList<Product> = ArrayList()
    var stock: ArrayList<Product> = ArrayList()
    override fun buy(product: Product?) {
        if (product is Bike) {
            bikes.add(product)
        } else if (product is BikePart) {
            stock.add(product)
        }
    }

    override fun sell(product: Product, customer: Customer) {
        customer.buy(product)
        customer.wallet -= product.price
        till += product.price
        if (product is Bike) {
            bikes.remove(product)
        }
        stock.remove(product)
    }

    fun stockCount(): Int {
        return stock.size
    }

    fun bikesCount(): Int {
        return bikes.size
    }

}