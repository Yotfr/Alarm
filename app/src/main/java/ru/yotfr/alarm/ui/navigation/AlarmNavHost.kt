package ru.yotfr.alarm.ui.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import ru.yotfr.alarm.ui.alarmlist.screen.AlarmsListScreen
import ru.yotfr.alarm.ui.alarmring.screen.AlarmRingScreen
import ru.yotfr.alarm.ui.createalarm.screen.CreateAlarmScreen


@Composable
fun AlarmNavHost() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screen.AlarmList.route
    ) {
        composable(
            route = Screen.AlarmList.route
        ) {
            AlarmsListScreen(
                navigateToCreateAlarmScreen = { id ->
                    navController.navigate(
                        route = Screen.CreateAlarm.passId(id)
                    )
                }
            )
        }
        composable(
            route = Screen.CreateAlarm.route,
            arguments = listOf(
                navArgument(NavigationConstants.ALARM_ID_ARGUMENT_KEY) {
                    nullable = true
                }
            )
        ) { backStackEntry ->
            Log.d("TEST"," Start composable CreateAlarm ( Navigation )")
            val alarmId = backStackEntry.arguments?.getString(NavigationConstants.ALARM_ID_ARGUMENT_KEY)
                ?.toLong()
            Log.d("TEST","AlarmID received $alarmId")
            CreateAlarmScreen(
                alarmId = alarmId,
                navigateBack = { navController.popBackStack() }
            )
        }
        composable(
            route = Screen.AlarmRing.route,
            arguments = listOf(
                navArgument(NavigationConstants.ALARM_ID_ARGUMENT_KEY) {
                    nullable = true
                }
            ),
            deepLinks = listOf(navDeepLink {
                uriPattern =
                    NavigationConstants.MY_URI_RING
            })
        ) {
            AlarmRingScreen(
                navigateBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}