package ru.yotfr.alarm.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navDeepLink
import ru.yotfr.alarm.ui.screen.AlarmRingScreen
import ru.yotfr.alarm.ui.screen.AlarmsListScreen
import ru.yotfr.alarm.ui.screen.CreateAlarmScreen

const val MY_URI_RING = "https://yotfr.com/ring"

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
        composable(
            route = "ring",
            deepLinks = listOf(navDeepLink { uriPattern = MY_URI_RING })
        ) {
            AlarmRingScreen()
        }
    }
}