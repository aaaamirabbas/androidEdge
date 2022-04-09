package io.github.aaaamirabbas.edge.base.architecture

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.aaaamirabbas.edge.ext.flowOnIO
import io.github.aaaamirabbas.edge.ext.launchOnIO
import io.github.aaaamirabbas.edge.utils.operation.OperationResult
import io.github.aaaamirabbas.edge.utils.time.TimeUtils
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

abstract class BaseViewModel : ViewModel() {
    fun <T> MutableLiveData<T>.setSafeValue(t: T?) {
        viewModelScope.launch { value = t }
    }

    fun <T> performOperation(
        operation: suspend () -> OperationResult<T>,
        result: (OperationResult<T>) -> Unit,
    ) = launchOnIO {
        flowOnIO<OperationResult<T>> {
            emit(OperationResult.doing(TimeUtils.getCurrentTime()))
            operation.invoke().also { emit(it) }
        }.collect { result(it) }
    }
}