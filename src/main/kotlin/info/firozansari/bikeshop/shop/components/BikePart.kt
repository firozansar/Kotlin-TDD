package info.firozansari.bikeshop.shop.components

import info.firozansari.bikeshop.shop.Product

abstract class BikePart(val make: String?, val size: Int, price: Int) : Product(price)