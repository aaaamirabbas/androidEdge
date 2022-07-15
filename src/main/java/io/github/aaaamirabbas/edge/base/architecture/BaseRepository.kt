package io.github.aaaamirabbas.edge.base.architecture

import io.github.aaaamirabbas.edge.utils.operation.OperationResult
import retrofit2.Response

abstract class BaseRepository {
    abstract suspend fun <T> getResult(call: suspend () -> Response<T>): OperationResult<T>
}