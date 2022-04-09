package io.github.aaaamirabbas.edge.ext.other

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

fun ViewModel.launchOnViewModelMain(
    block: suspend CoroutineScope.() -> Unit,
) = viewModelScope.launch { block(this) }

fun ViewModel.launchOnViewModelIO(
    block: suspend CoroutineScope.() -> Unit,
) = viewModelScope.launch(Dispatchers.IO) { block(this) }

fun ViewModel.launchOnViewModelCompute(
    block: suspend CoroutineScope.() -> Unit,
) = viewModelScope.launch(Dispatchers.Default) { block(this) }