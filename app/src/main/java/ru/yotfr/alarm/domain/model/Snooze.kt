package ru.yotfr.alarm.domain.model

enum class Snooze {
    OFF,
    FIVE,
    TEN,
    FIFTEEN,
    TWENTY,
    TWENTY_FIVE,
    THIRTY;

    fun minutes(): Int = when(this) {
        OFF -> throw IllegalArgumentException("Snooze turned off")
        FIVE -> 5
        TEN -> 10
        FIFTEEN -> 15
        TWENTY -> 20
        TWENTY_FIVE -> 25
        THIRTY -> 30
    }
}