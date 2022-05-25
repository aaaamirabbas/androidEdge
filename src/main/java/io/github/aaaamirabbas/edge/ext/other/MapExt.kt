package io.github.aaaamirabbas.edge.ext.other

fun Map<*, *>.logI(tag: String = "", throwable: Throwable? = null) {
    this.forEach {
        "${it.key}: ${it.value}".logI(tag, throwable)
    }
}