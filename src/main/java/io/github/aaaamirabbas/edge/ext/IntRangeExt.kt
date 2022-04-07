package io.github.aaaamirabbas.edge.ext

import kotlin.random.Random

fun IntRange.getRandom() =
    Random.nextInt(start, endInclusive + 1)