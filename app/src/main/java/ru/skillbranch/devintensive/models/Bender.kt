package ru.skillbranch.devintensive.models

import java.util.function.Predicate

class Bender(var status: Status = Status.NORMAL, var question: Question = Question.NAME) {
    fun askQuestion(): String {
        return question.question
    }

    fun listenAnswer(answer: String): Pair<String, Triple<Int, Int, Int>> {
        if (!question.validate(answer)) {
            return "${question.wrongMessage}\n${question.question}" to status.color
        }

        return if (question.answers.onEach { it.toLowerCase() }.contains(answer.toLowerCase())) {
            question = question.nextQuestion()
            "Отлично - ты справился\n${question.question}"
        } else {
            status = status.nextStatus()
            if (status == Status.NORMAL) {
                question = Question.NAME
                "Это неправильный ответ. Давай все по новой\n${question.question}"
            } else {
                "Это неправильный ответ\n${question.question}"
            }
        } to status.color
    }

    enum class Status(val color: Triple<Int, Int, Int>) {
        NORMAL(Triple(255, 255, 255)),
        WARNING(Triple(255, 120, 0)),
        DANGER(Triple(255, 60, 60)),
        CRITICAL(Triple(255, 0, 0));

        fun nextStatus(): Status {
            return if (this.ordinal < values().lastIndex) {
                values()[this.ordinal + 1]
            } else {
                values()[0]
            }
        }
    }

    enum class Question(
        val question: String,
        val answers: List<String>,
        private val validPredicate: Predicate<String>,
        val wrongMessage: String?
    ) {
        NAME(
            "Как меня зовут?",
            listOf("Бендер", "bender"),
            Predicate { it.isNotEmpty() && it[0].isUpperCase() },
            "Имя должно начинаться с заглавной буквы"
        ),
        PROFESSION(
            "Назови мою профессию?",
            listOf("сгибальщик", "bender"),
            Predicate { it.isNotEmpty() && it[0].isLowerCase() },
            "Профессия должна начинаться со строчной буквы"
        ),
        MATERIAL(
            "Из чего я сделан?",
            listOf("металл", "дерево", "metal", "iron", "wood"),
            Predicate { it.isNotEmpty() && !it.contains(Regex("\\d")) },
            "Материал не должен содержать цифр"
        ),
        BIRTHDAY(
            "Когда меня создали?",
            listOf("2993"),
            Predicate { it.isNotEmpty() && Regex("^\\d+$").matches(it) },
            "Год моего рождения должен содержать только цифры"
        ),
        SERIAL(
            "Мой серийный номер?",
            listOf("2716057"),
            Predicate { it.isNotEmpty() && it.matches(Regex("\\d{7}")) },
            "Серийный номер содержит только цифры, и их 7"
        ),
        IDLE("На этом все, вопросов больше нет", listOf(), Predicate { true }, null);

        fun nextQuestion(): Question {
            return if (ordinal < values().lastIndex) {
                values()[ordinal + 1]
            } else {
                return values()[values().lastIndex]
            }
        }

        fun validate(string: String): Boolean {
            return validPredicate.test(string)
        }
    }
}