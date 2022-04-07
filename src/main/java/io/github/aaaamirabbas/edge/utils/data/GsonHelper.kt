package io.github.aaaamirabbas.edge.utils.data

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class GsonHelper(
    private val gSon: Gson
) {
    fun Any.toJson(): String {
        return gSon.toJson(this)
    }

    fun <T> Any.toObject(clazz: Class<T>): T {
        return gSon.fromJson(this.toString(), clazz)
    }

    fun <T> Any.toAnyObject(clazz: Class<T>): T {
        return gSon.toJson(this).toObject(clazz)
    }

    fun <T> Any.toArrayObject(clazz: Class<T>): List<T> {
        val typeOfT = TypeToken.getParameterized(List::class.java, clazz).type
        return gSon.fromJson(this.toString(), typeOfT)
    }

    fun <T> Any.toAnyArrayObject(clazz: Class<T>): List<T> {
        return gSon.toJson(this).toArrayObject(clazz)
    }
}