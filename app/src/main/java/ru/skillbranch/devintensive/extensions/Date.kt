package ru.skillbranch.devintensive.extensions

import ru.skillbranch.devintensive.TimeUnits
import java.text.SimpleDateFormat
import java.util.*

fun Date.format(pattern: String = "HH:mm:ss dd.MM.yy"): String {
    val dateFormat = SimpleDateFormat(pattern, Locale("ru"))
    return dateFormat.format(this)
}

fun Date.add(value: Int, timeUnit: TimeUnits): Date {
    this.time = when (timeUnit) {
        TimeUnits.SECOND -> Date(this.time + 1000 * value)
        TimeUnits.MINUTE -> Date(this.time + 1000 * 60 * value)
        TimeUnits.HOUR -> Date(this.time + 1000 * 60 * 60 * value)
        TimeUnits.DAY -> Date(this.time + 1000 * 60 * 60 * 24 * value)
    }.time

    return this
}