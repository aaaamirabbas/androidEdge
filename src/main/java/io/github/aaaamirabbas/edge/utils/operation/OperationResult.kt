package io.github.aaaamirabbas.edge.utils.operation

data class OperationUIResult<T>(
    var onDoing: Long? = null,
    var onSuccess: T? = null,
    var onError: String? = null
) {
    companion object {
        fun <T> doing(onGoing: Long): OperationUIResult<T> {
            return OperationUIResult(onDoing = onGoing)
        }

        fun <T> error(onError: String): OperationUIResult<T> {
            return OperationUIResult(onError = onError)
        }

        fun <T> success(onSuccess: T): OperationUIResult<T> {
            return OperationUIResult(onSuccess = onSuccess)
        }
    }
}