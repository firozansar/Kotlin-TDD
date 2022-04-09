package info.firozansari.login

import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class LoginManagerTest {
    private lateinit var userRepo: HashMap<String, String>
    private lateinit var loginManager: LoginManager

    @BeforeEach
    fun setUp() {
        // Arrange
        userRepo = HashMap()
        userRepo["myuser"] = "mypassword"
        loginManager = LoginManager(userRepo)
    }

    @Test
    @Throws(EmptyPasswordException::class, InvalidCredentialsException::class)
    fun testLogin_CredentialsAreValid_ShouldReturnTrue() {
        // Arrange
        val username = "myuser"
        val password = "mypassword"

        // Act
        val result = loginManager.login(username, password)

        // Assert
        assertTrue(result)
    }

    // Assert
    @Test
    @Throws(InvalidCredentialsException::class)
    fun testLogin_CredentialsAreNotValid_ShouldThrowInvalidCredentialsException() {
        assertThrows<InvalidCredentialsException> {
            val username = "invaliduser"
            val password = "mypassword"
            loginManager.login(username, password)
        }
    }

    // Assert
    @Test
    @Throws(EmptyPasswordException::class)
    fun testLogin_PasswordIsEmpty_ShouldThrowEmptyPasswordException() {
        assertThrows<EmptyPasswordException> {
            val username = "invaliduser"
            loginManager.login(username, "")
        }
    }
}
