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
}