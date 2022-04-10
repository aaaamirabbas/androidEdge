package io.github.aaaamirabbas.edge.utils.moshi

import com.squareup.moshi.Moshi
import javax.inject.Inject

class MoshiUtils @Inject constructor(
    val moshi: Moshi
) {
    inline fun <reified T> toJson(obj: T): String {
        return moshi.adapter(T::class.java).toJson(obj)
    }

    inline fun <reified T> getSafeObject(json: String): Result<T?> {
        return runCatching {
            moshi.adapter(T::class.java).fromJson(json)
        }
    }
}