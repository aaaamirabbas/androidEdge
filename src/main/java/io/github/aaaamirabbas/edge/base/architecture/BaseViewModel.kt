package io.github.aaaamirabbas.edge.base.architecture

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.aaaamirabbas.edge.domain.model.FailureModel
import io.github.aaaamirabbas.edge.ext.other.flowOnIO
import io.github.aaaamirabbas.edge.ext.other.launchOnViewModelIO
import io.github.aaaamirabbas.edge.ext.other.launchOnViewModelMain
import io.github.aaaamirabbas.edge.utils.dateTime.TimeUtils
import io.github.aaaamirabbas.edge.utils.operation.OperationResult
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

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
                        TimeUtils.getCurrentMillis(), -1, "error"
                    )
                )
            )
        }.collect { result(it) }
    }
}