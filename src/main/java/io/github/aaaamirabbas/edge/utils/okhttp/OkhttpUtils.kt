package io.github.aaaamirabbas.edge.utils.okhttp

import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File


object OkhttpUtils {
    fun getTextRequestBody(text: String): RequestBody {
        return text.toRequestBody("text/plain".toMediaTypeOrNull())
    }

    fun getImageRequestBody(image: ByteArray): RequestBody {
        return image.toRequestBody("image/*".toMediaTypeOrNull())
    }

    fun getImagePart(file: File, partFileName: String): MultipartBody.Part {
        return MultipartBody.Part.createFormData(
            partFileName, file.name,
            file.asRequestBody("image/*".toMediaTypeOrNull())
        )
    }
}