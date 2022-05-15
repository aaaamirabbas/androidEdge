package io.github.aaaamirabbas.edge.utils.dateTime

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

object TimeUtils {

    fun getCurrentMillis() = System.currentTimeMillis()
    fun getCurrentSecond() = getSecond(getCurrentMillis())

    fun getSecond(unixTime: Long) = unixTime / 1000
    fun getUnixTime(second: Long) = second * 1000

    fun getTimerTick(
            scope: CoroutineScope,
            periodSecond: Long,
            totalPeriod: Long,
            block: suspend (remainingTime: Long) -> Unit
    ) = scope.launch {
        var total = totalPeriod
        while (total >= 0 && isActive) {
            block(total)
            delay(periodSecond * 1000)
            total -= periodSecond
        }
    }
}