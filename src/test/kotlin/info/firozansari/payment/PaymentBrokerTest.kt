package info.firozansari.payment

import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
class PaymentBrokerTest {

    @MockK
    private lateinit var  wallet: WalletInterface

    @MockK
    private lateinit var  provider: PaymentProviderInterface

    @MockK
    private lateinit var broker: PaymentBroker



    @Test
    @Throws(InsufficientFundsException::class, ProviderNotAvailableException::class)
    fun testPay_WalletHasFundsAndProviderIsAvailableAndDepositSucceeded_ShouldReturnTrue() {
        // Arrange
        val amount = 10
        val balance = 20

        every { wallet.balance } returns balance
        every { provider.isAvailable } returns true
        every { provider.deposit(wallet.id, amount) } returns true

        // Act & Assert
        Assertions.assertTrue(broker.pay(amount))
    }

    // Assert
    @Test
    @Throws(InsufficientFundsException::class, ProviderNotAvailableException::class)
    fun testPay_WalletDoesNotHaveFunds_ShouldThrowInsufficientFundsException() {
        // Arrange
        val amount = 10
        val balance = 9

        every { wallet.balance } returns balance
        every { provider.isAvailable } returns true
        every { provider.deposit(wallet.id, amount) } returns true

        // Act
        broker.pay(amount)
    }

    // Assert
    @Test
    @Throws(InsufficientFundsException::class, ProviderNotAvailableException::class)
    fun testPay_ProviderIsNotAvailable_ShouldThrowProviderNotAvailableException() {
        // Arrange
        val amount = 10
        val balance = 20

        every { wallet.balance } returns balance
        every { provider.isAvailable } returns false
        every { provider.deposit(wallet.id, amount) } returns true

        // Act
        broker.pay(amount)
    }
}