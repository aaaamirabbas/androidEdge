package io.github.aaaamirabbas.edge.base.architecture

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.github.aaaamirabbas.edge.domain.model.other.FailureModel
import io.github.aaaamirabbas.edge.ext.other.IECODE
import io.github.aaaamirabbas.edge.ext.other.flowOnIO
import io.github.aaaamirabbas.edge.ext.other.launchOnViewModelIO
import io.github.aaaamirabbas.edge.ext.other.launchOnViewModelMain
import io.github.aaaamirabbas.edge.utils.crashlytics.CrashlyticsUtils
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
        flowOnIO {
            emit(OperationResult.doing(TimeUtils.getCurrentMillis()))
            runCatching { operation.invoke() }
                .onSuccess { emit(it) }
                .onFailure {
                    CrashlyticsUtils.capture(it, this)
                    result(
                        OperationResult.failure(
                            FailureModel(
                                IECODE, "LOCAL DATA ERROR"
                            )
                        )
                    )
                }
        }.catch {
            result(
                OperationResult.failure(
                    FailureModel(
                        IECODE, "UNKNOWN LOCAL ERROR"
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