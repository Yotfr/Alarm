package ru.yotfr.alarm.ui.navigation

sealed class Screen(val route: String) {
    data object AlarmList : Screen("list")
    data object AlarmRing : Screen("ring/{${NavigationConstants.ALARM_ID_ARGUMENT_KEY}}") {
        fun passId(id: Long): String {
            return this.route.replace(
                oldValue = "{${NavigationConstants.ALARM_ID_ARGUMENT_KEY}}",
                newValue = id.toString()
            )
        }
    }

    data object CreateAlarm :
        Screen("create?alarmId={${NavigationConstants.ALARM_ID_ARGUMENT_KEY}}") {
        fun passId(id: Long?): String {
            return id?.let {
                this.route.replace(
                    oldValue = "{${NavigationConstants.ALARM_ID_ARGUMENT_KEY}}",
                    newValue = id.toString()
                )
            } ?: "create"
        }
    }
}