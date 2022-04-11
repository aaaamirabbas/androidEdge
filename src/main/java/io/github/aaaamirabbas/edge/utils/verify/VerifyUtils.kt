package io.github.aaaamirabbas.edge.utils.verify

object VerifyUtils {
    fun isEmailValid(email: String): Boolean {
        return email.matches(
            "^[A-Za-z0-9+_.-]+@(.+)$".toRegex()
        )
    }

}