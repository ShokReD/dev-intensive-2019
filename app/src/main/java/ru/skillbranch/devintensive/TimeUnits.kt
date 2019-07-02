package ru.skillbranch.devintensive

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
        val output = if (unknown) {
            "несколько ${this.forms[2]}"
        } else if (count == null) {
            this.forms[0]
        } else if (count.rem(10) == 1L && count.div(10) != 1L) {
            "$count ${this.forms[0]}"
        } else if (count in 2..4 && count.div(10) != 1L) {
            "$count ${this.forms[1]}"
        } else {
            "$count ${this.forms[2]}"
        }

        return if (after) "через $output" else "$output назад"
    }
}