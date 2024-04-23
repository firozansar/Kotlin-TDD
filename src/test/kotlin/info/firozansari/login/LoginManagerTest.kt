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
        // Given
        userRepo = HashMap()
        userRepo["myuser"] = "mypassword"
        loginManager = LoginManager(userRepo)
    }

    @Test
    fun `GIVEN valid credentials WHEN login is invoked THEN return True`() {
        // GIVEN
        val username = "myuser"
        val password = "mypassword"

        // WHEN
        val result = loginManager.login(username, password)

        // THEN
        assertTrue(result)
    }

    @Test
    fun `GIVEN invalid credentials WHEN login is invoked THEN throw InvalidCredentialsException`() {
        // GIVEN
        val username = "invaliduser"
        val password = "mypassword"

        // WHEN
        assertThrows<InvalidCredentialsException> {
            loginManager.login(username, password)
        }
    }

    @Test
    fun `GIVEN empty password WHEN login is invoked THEN throw EmptyPasswordException`() {
        // GIVEN
        val username = "invaliduser"
        val password = ""

        // WHEN
        assertThrows<EmptyPasswordException> {
            loginManager.login(username, password)
        }
    }
}
