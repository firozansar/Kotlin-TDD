package info.firozansari.bikeshop.shop.components

class Frame(make: String?, size: Int, val material: String, val type: String, private val colour: String, price: Int) :
    BikePart(make, size, price)