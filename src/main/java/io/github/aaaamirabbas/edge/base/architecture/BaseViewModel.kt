package io.github.aaaamirabbas.edge.base.architecture

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.github.aaaamirabbas.edge.domain.model.other.FailureModel
import io.github.aaaamirabbas.edge.ext.other.*
import io.github.aaaamirabbas.edge.utils.dateTime.TimeUtils
import io.github.aaaamirabbas.edge.utils.operation.OperationResult
import kotlinx.coroutines.flow.catch

abstract class BaseViewModel : ViewModel() {
    fun <T> MutableLiveData<T>.setSafeValue(t: T?) {
        launchOnViewModelMain { value = t }
    }

    fun <T> performOperation(
        operation: suspend () -> OperationResult<T>,
        result: (OperationResult<T>) -> Unit,
    ) = launchOnViewModelIO {
        flowOnIO<OperationResult<T>> {
            emit(OperationResult.doing(TimeUtils.getCurrentMillis()))
            operation.invoke().also { emit(it) }
        }.catch {
            result(
                OperationResult.failure(
                    FailureModel(
                        IECODE, "local error"
                    )
                )
            )
        }.collect {
            result(it)
        }
    }

    fun <T> performExec(
        operation: suspend () -> T,
        result: (T) -> Unit,
    ) = launchOnViewModelIO {
        operation().also {
            result(it)
        }
    }
}