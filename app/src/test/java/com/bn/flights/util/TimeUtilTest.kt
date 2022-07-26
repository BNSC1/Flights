package com.bn.flights.util

import org.junit.Test
import java.util.*

class TimeUtilTest {
    @Test
    fun getDateTime() {
        val ts = 1143239400L
        assert(
            "2006/03/25 06:30" == TimeUtil.getDateTime(
                ts,
                timeZone = TimeZone.getTimeZone("Asia/Taipei")
            ).also { println(it) })
        assert(
            "2006/03/24 22:30" == TimeUtil.getDateTime(
                ts,
                timeZone = TimeZone.getTimeZone("Europe/London")
            ).also { println(it) })
    }

    @Test
    fun getTimeZone() {}
}