package io.github.aaaamirabbas.edge.base.mvvm

import io.github.aaaamirabbas.edge.utils.data.Resource
import retrofit2.Response

abstract class BaseRepository {
    protected suspend fun <T> getResult(call: suspend () -> Response<T>): Resource<T> {
        try {
            val response = call()
            if (response.isSuccessful) {
                response.body()?.let {
                    return Resource.success(it)
                }
            }

            return error(" ${response.code()} ${response.message()}")
        } catch (e: Exception) {
            return error(e.message ?: e.toString())
        }
    }

    private fun <T> error(message: String): Resource<T> {
        return Resource.error("Network call has failed for a following reason: $message")
    }
}