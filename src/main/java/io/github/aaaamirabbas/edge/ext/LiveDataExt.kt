package io.github.aaaamirabbas.edge.ext

import androidx.lifecycle.LiveData

fun <T> LiveData<T>.toImmutable(): LiveData<T> = this