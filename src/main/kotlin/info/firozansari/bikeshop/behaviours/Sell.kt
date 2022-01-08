package info.firozansari.bikeshop.behaviours

import info.firozansari.bikeshop.people.Customer
import info.firozansari.bikeshop.shop.Product

interface Sell {
    fun sell(product: Product, customer: Customer)
}