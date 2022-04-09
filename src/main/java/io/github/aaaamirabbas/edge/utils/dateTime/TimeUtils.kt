package io.github.aaaamirabbas.edge.utils.dateTime

object TimeUtils {

    fun getCurrentMillis() = System.currentTimeMillis()
    fun getCurrentSecond() = getSecond(getCurrentMillis())

    fun getSecond(unixTime: Long) = unixTime / 1000
    fun getUnixTime(second: Long) = second * 1000
}