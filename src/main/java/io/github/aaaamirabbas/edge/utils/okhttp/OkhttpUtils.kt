package io.github.aaaamirabbas.edge.utils.okhttp

import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody

object OkhttpUtils {
    fun getTextRequestBody(text: String): RequestBody {
        return text.toRequestBody("text/plain".toMediaTypeOrNull())
    }
}