package io.github.aaaamirabbas.edge.ext.other


fun <T> Collection<T>.logE(tag: String = "", throwable: Throwable? = null) {
    this.forEach {
        it?.logE(tag, throwable)
    }
}
