package info.firozansari.login

import kotlin.Throws

class LoginManager(private val userRepo: Map<String, String>) {
    @Throws(EmptyPasswordException::class, InvalidCredentialsException::class)
    fun login(username: String, password: String): Boolean {
        if (userRepo.containsKey(username) && userRepo[username] === password) {
            return true
        }
        if (password.isEmpty()) {
            throw EmptyPasswordException()
        }
        throw InvalidCredentialsException()
    }
}