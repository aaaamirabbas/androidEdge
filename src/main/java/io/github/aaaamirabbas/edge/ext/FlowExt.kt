package io.github.aaaamirabbas.edge.ext

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

fun <T> flowIO(block: suspend FlowCollector<T>.() -> Unit) =
    flow { block() }.flowOn(Dispatchers.IO)