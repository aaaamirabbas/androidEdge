package io.github.aaaamirabbas.edge.ext

import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.util.*

fun String.toRequestBody(): RequestBody {
    return this.toRequestBody("text/plain".toMediaTypeOrNull())
}

fun String.applyValue(vararg args: Any?): String {
    return String.format(Locale.US, this, *args)
}