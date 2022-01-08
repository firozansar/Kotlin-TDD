package info.firozansari.payment

import info.firozansari.payment.WalletInterface
import info.firozansari.payment.PaymentProviderInterface
import kotlin.Throws
import info.firozansari.payment.InsufficientFundsException
import info.firozansari.payment.ProviderNotAvailableException

class PaymentBroker(private val wallet: WalletInterface, private val provider: PaymentProviderInterface) {
    @Throws(InsufficientFundsException::class, ProviderNotAvailableException::class)
    fun pay(amount: Int): Boolean {
        if (wallet.balance < amount) {
            throw InsufficientFundsException()
        }
        if (!provider.isAvailable) {
            throw ProviderNotAvailableException()
        }
        return provider.deposit(wallet.id, amount)
    }
}