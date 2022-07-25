package com.rdevl.sportplug2.utils

import java.text.SimpleDateFormat
import java.util.*

fun getTime(time: Long, pattern: String): String {
    val simpleDateFormat = SimpleDateFormat(pattern)
    val date = Date(time)
    return simpleDateFormat.format(date).toString()
}