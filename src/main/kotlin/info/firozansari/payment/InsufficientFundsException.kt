package info.firozansari.payment

import info.firozansari.payment.WalletInterface
import info.firozansari.payment.PaymentProviderInterface
import kotlin.Throws
import info.firozansari.payment.InsufficientFundsException
import info.firozansari.payment.ProviderNotAvailableException
import java.lang.Exception

class InsufficientFundsException : Exception()