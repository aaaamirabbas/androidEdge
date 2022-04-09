package io.github.aaaamirabbas.edge.base.architecture

import io.github.aaaamirabbas.edge.domain.model.other.FailureModel
import io.github.aaaamirabbas.edge.utils.dateTime.TimeUtils
import io.github.aaaamirabbas.edge.utils.operation.OperationResult
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
                    TimeUtils.getCurrentMillis(),
                    response.code(),
                    response.message()
                )
            )
        } catch (e: Exception) {
            return OperationResult.failure(
                FailureModel(
                    TimeUtils.getCurrentMillis(),
                    -1,
                    e.message ?: e.stackTrace.toString()
                )
            )
        }
    }
}