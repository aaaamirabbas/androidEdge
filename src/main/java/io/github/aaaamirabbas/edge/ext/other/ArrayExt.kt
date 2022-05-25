package io.github.aaaamirabbas.edge.ext.other

fun <T> Array<T>.logI(tag: String = "", throwable: Throwable? = null) {
    this.forEach {
        it?.logI(tag, throwable)
    }
}