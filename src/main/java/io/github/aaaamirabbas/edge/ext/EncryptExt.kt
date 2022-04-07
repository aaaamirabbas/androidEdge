package io.github.aaaamirabbas.edge.ext

import android.util.Base64
import java.io.UnsupportedEncodingException
import java.security.InvalidAlgorithmParameterException
import java.security.InvalidKeyException
import java.security.NoSuchAlgorithmException
import java.security.spec.AlgorithmParameterSpec
import javax.crypto.BadPaddingException
import javax.crypto.Cipher
import javax.crypto.IllegalBlockSizeException
import javax.crypto.NoSuchPaddingException
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec


private val ivBytes = byteArrayOf(
    0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
    0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00
)

@Throws(
    UnsupportedEncodingException::class,
    NoSuchAlgorithmException::class,
    NoSuchPaddingException::class,
    InvalidKeyException::class,
    InvalidAlgorithmParameterException::class,
    IllegalBlockSizeException::class,
    BadPaddingException::class
)
fun encrypt(
    key: String,
    text: String
): ByteArray? {
    val ivSpec: AlgorithmParameterSpec = IvParameterSpec(ivBytes)
    val newKey = SecretKeySpec(key.toByteArray(Charsets.UTF_8), "AES")
    var cipher: Cipher? = null
    cipher = Cipher.getInstance("AES/CBC/PKCS5Padding")
    cipher.init(Cipher.ENCRYPT_MODE, newKey, ivSpec)
    return cipher.doFinal(text.toByteArray(Charsets.UTF_8))
}

@Throws(
    UnsupportedEncodingException::class,
    NoSuchAlgorithmException::class,
    NoSuchPaddingException::class,
    InvalidKeyException::class,
    InvalidAlgorithmParameterException::class,
    IllegalBlockSizeException::class,
    BadPaddingException::class
)
fun decrypt(
    key: String,
    text: String
): ByteArray? {
    val ivSpec: AlgorithmParameterSpec = IvParameterSpec(ivBytes)
    val newKey = SecretKeySpec(key.toByteArray(Charsets.UTF_8), "AES")
    val cipher: Cipher = Cipher.getInstance("AES/CBC/PKCS5Padding")
    cipher.init(Cipher.DECRYPT_MODE, newKey, ivSpec)
    return cipher.doFinal(text.toByteArray(Charsets.UTF_8))
}


fun String.toBase64(): String {
    return Base64.encodeToString(
        this.toByteArray(Charsets.UTF_8),
        Base64.NO_WRAP
    ).toString()
}

fun String.fromBase64(): String {
    return Base64.decode(this, Base64.NO_WRAP)
        .toString(Charsets.UTF_8)
}
