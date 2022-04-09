package io.github.aaaamirabbas.edge.base.mvvm

import androidx.lifecycle.ViewModel
import io.github.aaaamirabbas.edge.ext.flowOnIO
import io.github.aaaamirabbas.edge.ext.launchOnIO
import io.github.aaaamirabbas.edge.utils.operation.OperationResult
import io.github.aaaamirabbas.edge.utils.operation.OperationStatus
import io.github.aaaamirabbas.edge.utils.time.TimeUtils
import kotlinx.coroutines.flow.collect

abstract class BaseViewModel : ViewModel() {
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