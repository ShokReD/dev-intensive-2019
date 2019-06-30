package ru.skillbranch.devintensive.models

import java.util.*

data class User(
    val id: String,
    var firstName: String?,
    var lastName: String?,
    var avatar: String?,
    var rating: Int = 0,
    var respect: Int = 0,
    var lastVisit: Date? = Date(),
    var isOnline: Boolean = false
) {
    companion object Factory {
        private var id = 0

        fun makeUser(fullName: String?): User {
            val list = fullName?.split(" ")

            return User(
                id = "${++id}",
                firstName = list?.get(0),
                lastName = list?.get(1),
                avatar = null
            )
        }
    }
}