package io.github.aaaamirabbas.edge.utils.theme

import java.util.*

object ThemeUtils {
    fun isNight(): Boolean {
        val currentHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
        return (currentHour <= 7 || currentHour >= 18)
    }
}
