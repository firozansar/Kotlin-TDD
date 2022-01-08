package info.firozansari.payment

interface PaymentProviderInterface {
    val isAvailable: Boolean
    fun deposit(id: Int, amount: Int): Boolean
}