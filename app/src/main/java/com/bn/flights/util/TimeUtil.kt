package com.bn.flights.util

import java.text.SimpleDateFormat
import java.util.*

object TimeUtil {
    fun getDateTime(
        ts: Long,
        pattern: String = TIME_PATTERN,
        timeZone: TimeZone = TimeZone.getDefault()
    ): String =
        SimpleDateFormat(pattern).run {
            this.timeZone = timeZone
            format(Date(ts * 1000L))
        }

    fun getTimeZoneOffset(): String = SimpleDateFormat("Z").format(Date())

    private const val TIME_PATTERN = "yyyy/MM/dd HH:mm"
}