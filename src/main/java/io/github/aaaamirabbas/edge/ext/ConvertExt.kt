package io.github.aaaamirabbas.edge.ext

import java.util.*

fun Int.toSplit(): String {
    return String.format(Locale.US, "%,d", this)
}

fun Float.toSplit(): String {
    return String.format(Locale.US, "%,.2f", this)
}