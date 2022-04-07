package io.github.aaaamirabbas.edge.ext

import java.security.MessageDigest

fun Any.toMD5(): String {
    return MessageDigest.getInstance("MD5")
        .digest(this.toString().toByteArray())
        .joinToString("") {
            "%02x".format(it)
        }
}

fun Any.toSHA256(): String {
    val bytes = this.toString().toByteArray()
    val md = MessageDigest.getInstance("SHA-256")
    val digest = md.digest(bytes)
    return digest.fold("") { str, it -> str + "%02x".format(it) }
}