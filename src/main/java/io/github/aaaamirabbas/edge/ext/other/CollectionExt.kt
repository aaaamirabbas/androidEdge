package io.github.aaaamirabbas.edge.ext.other


fun <T> Collection<T>.logI(tag: String = "", throwable: Throwable? = null) {
    this.forEach {
        it?.logI(tag, throwable)
    }
}
