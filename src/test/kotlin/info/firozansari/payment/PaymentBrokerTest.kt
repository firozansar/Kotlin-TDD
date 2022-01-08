package info.firozansari.payment

import io.mockk.junit5.MockKExtension
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
class PaymentBrokerTest {
    private val wallet: WalletInterface? = null
    private val provider: PaymentProviderInterface? = null
    private var broker: PaymentBroker? = null
    @BeforeEach
    fun setUp() {
        // Arrange
        //wallet = mock(WalletInterface.class);
        //provider = mock(PaymentProviderInterface.class);
        broker = PaymentBroker(wallet!!, provider!!)
    }

    @Test
    @Throws(InsufficientFundsException::class, ProviderNotAvailableException::class)
    fun testPay_WalletHasFundsAndProviderIsAvailableAndDepositSucceeded_ShouldReturnTrue() {
        // Arrange
        val amount = 10
        val balance = 20

//        when(wallet.getBalance()).thenReturn(balance);
//        when(provider.isAvailable()).thenReturn(true);
//        when(provider.deposit(wallet.getId(), amount)).thenReturn(true);

        // Act & Assert
        Assertions.assertTrue(broker!!.pay(amount))
    }

    // Assert
    @Test
    @Throws(InsufficientFundsException::class, ProviderNotAvailableException::class)
    fun testPay_WalletDoesNotHaveFunds_ShouldThrowInsufficientFundsException() {
        // Arrange
        val amount = 10
        val balance = 9

//        when(wallet.getBalance()).thenReturn(balance);
//        when(provider.isAvailable()).thenReturn(true);
//        when(provider.deposit(wallet.getId(), amount)).thenReturn(true);

        // Act
        broker!!.pay(amount)
    }

    // Assert
    @Test
    @Throws(InsufficientFundsException::class, ProviderNotAvailableException::class)
    fun testPay_ProviderIsNotAvailable_ShouldThrowProviderNotAvailableException() {
        // Arrange
        val amount = 10
        val balance = 20

//        when(wallet.getBalance()).thenReturn(balance);
//        when(provider.isAvailable()).thenReturn(false);
//        when(provider.deposit(wallet.getId(), amount)).thenReturn(true);

        // Act
        broker!!.pay(amount)
    }
}