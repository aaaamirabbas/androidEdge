package io.github.aaaamirabbas.edge.ext.other

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.ProducerScope
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn


fun <T> flowOnIO(
    block: suspend FlowCollector<T>.() -> Unit,
) = flow { block() }.flowOn(Dispatchers.IO)

fun <T> channelFlowOnIO(
    block: suspend ProducerScope<T>.() -> Unit,
) = channelFlow { block() }.flowOn(Dispatchers.IO)

fun <T> flowOnCompute(
    block: suspend FlowCollector<T>.() -> Unit,
) = flow { block() }.flowOn(Dispatchers.Default)

fun <T> flowOnMain(
    block: suspend FlowCollector<T>.() -> Unit,
) = flow { block() }.flowOn(Dispatchers.Main)