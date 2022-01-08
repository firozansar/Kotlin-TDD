package info.firozansari.login

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class LoginManagerTest {
    private var userRepo: HashMap<String, String>? = null
    private var loginManager: LoginManager? = null
    @BeforeEach
    fun setUp() {
        // Arrange
        userRepo = HashMap()
        userRepo!!["myuser"] = "mypassword"
        loginManager = LoginManager(userRepo!!)
    }

    @Test
    @Throws(EmptyPasswordException::class, InvalidCredentialsException::class)
    fun testLogin_CredentialsAreValid_ShouldReturnTrue() {
        // Arrange
        val username = "myuser"
        val password = "mypassword"

        // Act
        val result = loginManager!!.login(username, password)

        // Assert
        Assertions.assertTrue(result)
    }

    // Assert
    @Test
    @Throws(EmptyPasswordException::class, InvalidCredentialsException::class)
    fun testLogin_CredentialsAreNotValid_ShouldThrowInvalidCredentialsException() {
        // Arrange
        val username = "invaliduser"
        val password = "mypassword"

        // Act
        loginManager!!.login(username, password)
    }

    // Assert
    @Test
    @Throws(EmptyPasswordException::class, InvalidCredentialsException::class)
    fun testLogin_PasswordIsEmpty_ShouldThrowEmptyPasswordException() {
        // Arrange
        val username = "invaliduser"

        // Act
        loginManager!!.login(username, "")
    }
}