package io.github.aaaamirabbas.edge.ext.other

fun Boolean.toInt(): Int {
    return if (this) 1 else 0
}