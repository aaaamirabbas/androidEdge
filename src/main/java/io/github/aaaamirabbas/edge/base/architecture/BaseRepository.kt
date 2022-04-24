package io.github.aaaamirabbas.edge.base.architecture

import io.github.aaaamirabbas.edge.utils.operation.OperationResult
import retrofit2.Response

data class ErrorResponse(
    val success: Boolean = false,
    val message: String?
)

abstract class BaseRepository {
    abstract suspend fun <T> getResult(call: suspend () -> Response<T>): OperationResult<T>
}