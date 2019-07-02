package ru.skillbranch.devintensive.models

import ru.skillbranch.devintensive.utils.Utils
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
        private var lastId = 0

        fun makeUser(fullName: String?): User {
            val (firstName, lastName) = Utils.parseFullName(fullName)

            return User(
                id = "${++lastId}",
                firstName = firstName,
                lastName = lastName,
                avatar = null
            )
        }

        fun Builder(): Builder {
            return Builder(++lastId)
        }
    }

    class Builder(lastId: Int) {
        var id: String = lastId.toString()
        var firstName: String? = null
        var lastName: String? = null
        var avatar: String? = null
        var rating: Int = 0
        var respect: Int = 0
        var lastVisit: Date? = Date()
        var isOnline: Boolean = false

        fun id(s: String): Builder {
            this.id = s
            return this
        }

        fun firstName(s: String): Builder {
            this.firstName = s
            return this
        }

        fun lastName(s: String): Builder {
            this.lastName = s
            return this
        }

        fun avatar(s: String): Builder {
            this.avatar = s
            return this
        }

        fun rating(s: Int): Builder {
            this.rating = s
            return this
        }

        fun respect(s: Int): Builder {
            this.respect = s
            return this
        }

        fun lastVisit(s: Date): Builder {
            this.lastVisit = s
            return this
        }

        fun isOnline(s: Boolean): Builder {
            this.isOnline = s
            return this
        }

        fun build(): User {
            return User(id, firstName, lastName, avatar, rating, respect, lastVisit, isOnline)
        }
    }
}
