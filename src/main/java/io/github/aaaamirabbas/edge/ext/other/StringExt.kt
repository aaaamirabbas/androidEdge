package io.github.aaaamirabbas.edge.ext.other

import java.util.*

fun String.fmt(vararg args: Any?): String {
    return String.format(Locale.US, this, *args)
}