package ru.skillbranch.devintensive.utils

object Utils {
    fun parseFullName(fullName: String?): Pair<String?, String?> {
        if (fullName.isNullOrBlank()) {
            return null to null
        }

        val list = fullName.split(" ")

        return list.getOrNull(0) to list.getOrNull(1)
    }
}