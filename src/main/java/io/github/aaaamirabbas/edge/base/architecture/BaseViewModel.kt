package io.github.aaaamirabbas.edge.base.architecture

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

abstract class BaseViewModel : ViewModel() {
    fun <T> MutableLiveData<T>.setSafeValue(t: T?) {
        viewModelScope.launch { value = t }
    }
}