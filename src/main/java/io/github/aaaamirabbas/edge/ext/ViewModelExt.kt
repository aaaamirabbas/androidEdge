package io.github.aaaamirabbas.edge.ext

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

fun ViewModel.launchOnMain(block: suspend () -> Unit) {
    viewModelScope.launch {
        block()
    }
}

fun ViewModel.launchOnIO(block: suspend () -> Unit) {
    viewModelScope.launch(Dispatchers.IO) {
        block()
    }
}