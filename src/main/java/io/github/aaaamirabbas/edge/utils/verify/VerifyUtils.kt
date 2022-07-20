package io.github.aaaamirabbas.edge.utils.verify

object VerifyUtils {
    fun isEmail(email: String): Boolean {
        return email.matches(
            "^[A-Za-z0-9+_.-]+@(.+)$".toRegex()
        )
    }

    fun isPhoneNumber(mobileNumber: String): Boolean {
        return mobileNumber.matches(
            "^[\\+]?[(]?[0-9]{3}[)]?[-\\s\\.]?[0-9]{3}[-\\s\\.]?[0-9]{4,6}\$".toRegex()
        )
    }

    fun isIRPhoneNumber(phone: String): Boolean {
        return isPhoneNumber(phone) && phone.matches(
            "^(\\+98|0)?9\\d{9}\$".toRegex()
        )
    }
}