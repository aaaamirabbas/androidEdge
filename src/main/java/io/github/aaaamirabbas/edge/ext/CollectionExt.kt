package io.github.aaaamirabbas.edge.ext


fun <T> Collection<T>.logListE(tag: String = "") {
    this.forEach {
        it?.logE(tag)
    }
}

fun <T> Array<T>.logArrayE(tag: String = "") {
    this.forEach {
        it?.logE(tag)
    }
}

fun <K, V> Map<*, *>.logMapE(tag: String = "") {
    this.forEach {
        this.logE("$tag${it.key}: ${it.value}")
    }
}

