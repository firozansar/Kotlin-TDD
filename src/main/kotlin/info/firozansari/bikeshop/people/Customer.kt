package info.firozansari.bikeshop.people

import info.firozansari.bikeshop.behaviours.IBuy
import info.firozansari.bikeshop.shop.Product

class Customer(name: String?, var wallet: Int) : Person(name), IBuy {
    var basket: ArrayList<Product> = ArrayList()
    val collection: ArrayList<Product?> = ArrayList()

    fun basketCount(): Int {
        return basket.size
    }

    fun addToBasket(product: Product) {
        basket.add(product)
    }

    fun collectionCount(): Int {
        return collection.size
    }

    override fun buy(product: Product?) {
        collection.add(product)
        var total = 0
        for (productInBasket in basket) {
            total += productInBasket.price
        }
        wallet -= total
    }

}