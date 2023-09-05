package ru.yotfr.alarm.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ru.yotfr.alarm.ui.screen.AlarmsListScreen
import ru.yotfr.alarm.ui.screen.CreateAlarmScreen

@Composable
fun AlarmNavHost() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "list") {
        composable("list") {
            AlarmsListScreen(
                navigateToCreateAlarmScreen = {
                    navController.navigate("create")
                }
            )
        }
        composable("create") {
            CreateAlarmScreen()
        }
    }
}