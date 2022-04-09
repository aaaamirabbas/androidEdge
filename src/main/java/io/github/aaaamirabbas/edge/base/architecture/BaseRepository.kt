package io.github.aaaamirabbas.edge.base.architecture

import io.github.aaaamirabbas.edge.domain.model.FailureModel
import io.github.aaaamirabbas.edge.utils.operation.OperationResult
import io.github.aaaamirabbas.edge.utils.time.TimeUtils
import retrofit2.Response

abstract class BaseRepository {
    suspend fun <T> getResult(call: suspend () -> Response<T>): OperationResult<T> {
        try {
            val response = call()
            if (response.isSuccessful) {
                response.body()?.let {
                    return OperationResult.success(it)
                }
            }

            return OperationResult.failure(
                FailureModel(
                    TimeUtils.getCurrentTime(),
                    response.code(),
                    response.message()
                )
            )
        } catch (e: Exception) {
            return OperationResult.failure(
                FailureModel(
                    TimeUtils.getCurrentTime(),
                    -1,
                    e.message ?: e.stackTrace.toString()
                )
            )
        }
    }
}