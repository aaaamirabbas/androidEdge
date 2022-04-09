package io.github.aaaamirabbas.edge.ext.other

import kotlinx.coroutines.*

suspend fun <T> withIO(
    block: suspend CoroutineScope.() -> T,
) = withContext(Dispatchers.IO) { block(this) }

suspend fun <T> withCompute(
    block: suspend CoroutineScope.() -> T,
) = withContext(Dispatchers.Default) { block(this) }

suspend fun <T> withMain(
    block: suspend CoroutineScope.() -> T,
) = withContext(Dispatchers.Main.immediate) { block(this) }

fun <T> debounce(
    waitMs: Long = 300L,
    scope: CoroutineScope,
    destinationFunction: (T) -> Unit
): (T) -> Unit {
    var debounceJob: Job? = null
    return { param: T ->
        debounceJob?.cancel()
        debounceJob = scope.launch {
            delay(waitMs)
            destinationFunction(param)
        }
    }
}

fun <T> debounceCancelable(
    waitMs: Long = 300L,
    scope: CoroutineScope,
    destinationFunction: (T) -> Unit
): (T?) -> Unit {
    var debounceJob: Job? = null
    return { param: T? ->
        debounceJob?.cancel()
        if (param != null)
            debounceJob = scope.launch {
                delay(waitMs)
                destinationFunction(param)
            }
    }
}