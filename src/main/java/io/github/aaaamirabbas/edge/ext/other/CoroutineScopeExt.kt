package io.github.aaaamirabbas.edge.ext.other

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async

fun <T> CoroutineScope.asyncCompute(
    start: CoroutineStart = CoroutineStart.DEFAULT,
    block: suspend CoroutineScope.() -> T
) = async(Dispatchers.Default, start, block)

fun <T> CoroutineScope.asyncIO(
    start: CoroutineStart = CoroutineStart.DEFAULT,
    block: suspend CoroutineScope.() -> T
) = async(Dispatchers.IO, start, block)

fun <T> CoroutineScope.asyncMain(
    start: CoroutineStart = CoroutineStart.DEFAULT,
    block: suspend CoroutineScope.() -> T
) = async(Dispatchers.Main.immediate, start, block)
