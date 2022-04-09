package io.github.aaaamirabbas.edge.base.mvvm

import io.github.aaaamirabbas.edge.utils.operation.OperationResult
import retrofit2.Response

abstract class BaseRepository {
    protected suspend fun <T> getResult(call: suspend () -> Response<T>): OperationResult<T> {
        try {
            val response = call()
            if (response.isSuccessful) {
                response.body()?.let {
                    return OperationResult.success(it)
                }
            }

            return OperationResult.failure(" ${response.code()} ${response.message()}")
        } catch (e: Exception) {
            return OperationResult.failure(e.message ?: e.toString())
        }
    }
}