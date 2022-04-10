package io.github.aaaamirabbas.edge.utils.moshi

import com.squareup.moshi.Moshi
import javax.inject.Inject


class MoshiUtils @Inject constructor(
    val moshi: Moshi
) {
    inline fun <reified T> toJson(obj: T): String {
        return moshi.adapter(T::class.java).nullSafe().toJson(obj)
    }

    inline fun <reified T> getObject(json: String): T? {
        return moshi.adapter(T::class.java).nullSafe().fromJson(json)
    }

    inline fun <reified T> getListObject(json: String): List<T>? {
        return moshi.adapter<List<T>>(T::class.java).nullSafe().fromJson(json)
    }
}