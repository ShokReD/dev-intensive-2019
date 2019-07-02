package ru.skillbranch.devintensive.utils

object Utils {
    fun parseFullName(fullName: String?): Pair<String?, String?> {
        if (fullName.isNullOrBlank()) {
            return null to null
        }

        val list = fullName.split(" ")

        return list.getOrNull(0) to list.getOrNull(1)
    }

    fun toInitials(firstName: String?, lastName: String?): String? {
        val fn = firstName?.toUpperCase()
        val ln = lastName?.toUpperCase()

        return if (fn.isNullOrBlank()) {
            if (ln.isNullOrBlank()) {
                null
            } else {
                ln[0].toString()
            }
        } else {
            if (ln.isNullOrBlank()) {
                fn[0].toString()
            } else {
                fn[0].toString() + ln[0].toString()
            }
        }
    }

    private fun String.toUpperFirst(): String {
        return this[0].toUpperCase() + this.substring(1)
    }

    fun transliteration(payload: String?, divider: String = " "): String {
        val stringBuilder = StringBuilder()
        payload?.map {
            if (it.isUpperCase()) {
                mapTranslite(it, divider).toUpperFirst()
            } else {
                mapTranslite(it, divider)
            }
        }?.joinTo(stringBuilder, separator = "")

        return stringBuilder.toString()
    }

    private fun mapTranslite(char: Char, divider: String): String {
        return when (char.toLowerCase()) {
            'а' -> "a"
            'б' -> "b"
            'в' -> "v"
            'г' -> "g"
            'д' -> "d"
            'е' -> "e"
            'ё' -> "e"
            'ж' -> "zh"
            'з' -> "z"
            'и' -> "i"
            'й' -> "i"
            'к' -> "k"
            'л' -> "l"
            'м' -> "m"
            'н' -> "n"
            'о' -> "o"
            'п' -> "p"
            'р' -> "r"
            'с' -> "s"
            'т' -> "t"
            'у' -> "u"
            'ф' -> "f"
            'х' -> "h"
            'ц' -> "c"
            'ч' -> "ch"
            'ш' -> "sh"
            'щ' -> "sh'"
            'ъ' -> ""
            'ы' -> "i"
            'ь' -> ""
            'э' -> "e"
            'ю' -> "yu"
            'я' -> "ya"
            ' ' -> divider
            else -> char.toString()
        }
    }
}