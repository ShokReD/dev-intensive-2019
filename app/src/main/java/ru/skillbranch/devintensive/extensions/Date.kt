package ru.skillbranch.devintensive.extensions

import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.abs

fun Date.format(pattern: String = "HH:mm:ss dd.MM.yy"): String {
    val dateFormat = SimpleDateFormat(pattern, Locale("ru"))
    return dateFormat.format(this)
}

fun Date.add(value: Int, timeUnit: TimeUnits): Date {
    this.time = when (timeUnit) {
        TimeUnits.MILLISECOND -> Date(this.time + value)
        TimeUnits.SECOND -> Date(this.time + 1000L * value)
        TimeUnits.MINUTE -> Date(this.time + 1000L * 60 * value)
        TimeUnits.HOUR -> Date(this.time + 1000L * 60 * 60 * value)
        TimeUnits.DAY -> Date(this.time + 1000L * 60 * 60 * 24 * value)
        TimeUnits.WEEK -> Date(this.time + 1000L * 60 * 60 * 24 * 7 * value)
        else -> throw UnsupportedOperationException()
    }.time

    return this
}

enum class TimeUnit(
    private val forms: Array<String>
) {
    SECOND(arrayOf("секунду", "секунды", "секунд")),
    MINUTE(arrayOf("минуту", "минуты", "минут")),
    HOUR(arrayOf("час", "часа", "часов")),
    DAY(arrayOf("день", "дня", "дней")),
    WEEK(arrayOf("неделю", "недели", "недель")),
    MONTH(arrayOf("месяц", "месяца", "месяцев")),
    YEAR(arrayOf("год", "года", "лет"));

    fun format(count: Long? = null, unknown: Boolean = false, after: Boolean = false): String {
        val output = if (unknown) {
            "несколько ${this.forms[2]}"
        } else if (count == null) {
            this.forms[0]
        } else if (count.rem(10) == 1L && count.div(10) != 1L) {
            "$count ${this.forms[0]}"
        } else if (count.rem(10) in 2..4 && count.div(10) != 1L) {
            "$count ${this.forms[1]}"
        } else {
            "$count ${this.forms[2]}"
        }

        return if (after) "через $output" else "$output назад"
    }
}

fun Date.humanizeDiff(): String {
    val now = Date()
    var millis = now.time - this.time
    val after = this.after(now)
    millis = abs(millis)

    return when (millis) {
        in ONE_SECOND -> "только что"
        in FEW_SECOND -> TimeUnit.SECOND.format(unknown = true, after = after)
        in ONE_MINUTE -> TimeUnit.MINUTE.format(after = after)
        in FEW_MINUTE -> TimeUnit.MINUTE.format(millis / 60 / 1000, after = after)
        in ONE_HOUR -> TimeUnit.HOUR.format(after = after)
        in FEW_HOUR -> TimeUnit.HOUR.format(millis / 60 / 60 / 1000, after = after)
        in ONE_DAY -> TimeUnit.DAY.format(after = after)
        in FEW_DAY -> TimeUnit.DAY.format(millis / 24 / 60 / 60 / 1000, after = after)
        in ONE_YEAR -> "более ${if (after) "чем через год" else "года назад"}"
        else -> throw IllegalArgumentException("Wrong type")
    }
}

private val ONE_SECOND = LongRange(0, 1000)
private val FEW_SECOND = LongRange(ONE_SECOND.last + 1, 45L * 1000)
private val ONE_MINUTE = LongRange(FEW_SECOND.last + 1, 75L * 1000)
private val FEW_MINUTE = LongRange(ONE_MINUTE.last + 1, 45L * 60 * 1000)
private val ONE_HOUR = LongRange(FEW_MINUTE.last + 1, 75L * 60 * 1000)
private val FEW_HOUR = LongRange(ONE_HOUR.last + 1, 22L * 60 * 60 * 1000)
private val ONE_DAY = LongRange(FEW_HOUR.last + 1, 26L * 60 * 60 * 1000)
private val FEW_DAY = LongRange(ONE_DAY.last + 1, 360L * 24 * 60 * 60 * 1000)
private val ONE_YEAR = LongRange(FEW_DAY.last + 1, Long.MAX_VALUE)

enum class TimeUnits(
    private val forms: Array<String>
) {
    MILLISECOND(arrayOf("милисекунду", "милисекунды", "милисекунд")),
    SECOND(arrayOf("секунду", "секунды", "секунд")),
    MINUTE(arrayOf("минуту", "минуты", "минут")),
    HOUR(arrayOf("час", "часа", "часов")),
    DAY(arrayOf("день", "дня", "дней")),
    WEEK(arrayOf("неделю", "недели", "недель")),
    MONTH(arrayOf("месяц", "месяца", "месяцев")),
    YEAR(arrayOf("год", "года", "лет"));

    fun format(count: Long? = null, unknown: Boolean = false, after: Boolean = false): String {
        val output = plural(count, unknown)

        return if (after) "через $output" else "$output назад"
    }

    fun plural(count: Long?, unknown: Boolean = false): String {
        return if (unknown) {
            "несколько ${this.forms[2]}"
        } else if (count == null) {
            this.forms[0]
        } else if (count.rem(10) == 1L && count.div(10) != 1L) {
            "$count ${this.forms[0]}"
        } else if (count.rem(10) in 2..4 && count.div(10) != 1L) {
            "$count ${this.forms[1]}"
        } else {
            "$count ${this.forms[2]}"
        }
    }
}