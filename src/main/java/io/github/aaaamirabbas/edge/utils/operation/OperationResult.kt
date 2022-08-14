package io.github.aaaamirabbas.edge.utils.operation

import androidx.annotation.Keep
import io.github.aaaamirabbas.edge.domain.model.other.FailureModel

@Keep
data class OperationResult<T>(
    private var onDoing: Long? = null,
    private var onSuccess: T? = null,
    private var onFailure: FailureModel? = null
) {
    companion object {
        fun <T> doing(onDoing: Long): OperationResult<T> {
            return OperationResult(onDoing = onDoing)
        }

        fun <T> failure(onFailure: FailureModel): OperationResult<T> {
            return OperationResult(onFailure = onFailure)
        }

        fun <T> success(onSuccess: T): OperationResult<T> {
            return OperationResult(onSuccess = onSuccess)
        }
    }

    fun onDoing(onDoing: (Long) -> Unit): OperationResult<T> {
        this.onDoing?.let { onDoing(it) }
        return this
    }

    fun onSuccess(onSuccess: (T) -> Unit): OperationResult<T> {
        this.onSuccess?.let { onSuccess(it) }
        return this
    }

    fun onFailure(onFailure: (FailureModel) -> Unit): OperationResult<T> {
        this.onFailure?.let { onFailure(it) }
        return this
    }
}