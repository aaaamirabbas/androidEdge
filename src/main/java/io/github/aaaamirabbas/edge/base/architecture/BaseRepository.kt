package io.github.aaaamirabbas.edge.base.architecture

import io.github.aaaamirabbas.edge.domain.model.other.FailureModel
import io.github.aaaamirabbas.edge.ext.other.IECODE
import io.github.aaaamirabbas.edge.utils.gson.GsonUtils
import io.github.aaaamirabbas.edge.utils.operation.OperationResult
import retrofit2.Response

data class ErrorResponse(
    val success: Boolean = false,
    val message: String?
)

abstract class BaseRepository(
    private val gsonUtils: GsonUtils,
) {
    suspend fun <T> getResult(call: suspend () -> Response<T>): OperationResult<T> {
        kotlin.runCatching { call() }.onSuccess { response ->
            if (response.isSuccessful) {
                response.body()?.let {
                    return OperationResult.success(it)
                }
            }

            gsonUtils.getSafeObject<ErrorResponse>(
                response.errorBody()?.string().toString()
            )
                .onSuccess {
                    return OperationResult.failure(
                        FailureModel(
                            response.code(),
                            it.message ?: response.message()
                        )
                    )
                }.onFailure {
                    return OperationResult.failure(
                        FailureModel(
                            response.code(),
                            response.message()
                        )
                    )
                }
        }.onFailure { e ->
            return OperationResult.failure(
                FailureModel(
                    IECODE, e.message ?: e.stackTrace.toString()
                )
            )
        }

        return OperationResult.failure(
            FailureModel(
                IECODE, "Unknown error"
            )
        )
    }
}