package com.heckcodes.essentialish.ui.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.navArgument
import com.heckcodes.essentialish.presentation.viewmodel.SettingScreenViewModel
import com.heckcodes.essentialish.ui.screens.ListDetailScreen
import com.heckcodes.essentialish.ui.screens.ListsScreen
import com.heckcodes.essentialish.ui.screens.SettingsHomeScreen
import com.heckcodes.essentialish.ui.screens.StatsScreen
import com.heckcodes.essentialish.ui.screens.setting.SettingsListItemPreferencesScreen
import com.heckcodes.essentialish.ui.screens.setting.SettingsThemeScreen

@Composable
fun AppNavGraph(
    navController: NavHostController,
    startDestination: String,
    innerPadding: PaddingValues
) {
    NavHost(navController = navController, startDestination = startDestination) {
        composable(ScreenRoute.Lists.routeName) { ListsScreen(innerPadding) }
        composable(
            route = ScreenRoute.ListDetails.routeName,
            arguments = listOf(
                navArgument("list_id") {
                    type = NavType.StringType
                    nullable = true
                    defaultValue = null
                }
            )
        ) { backStackEntry ->
            val listId = backStackEntry.arguments?.getString("list_id")?.toLongOrNull()
            ListDetailScreen(listId, innerPadding)
        }

        composable(ScreenRoute.Stats.routeName) { StatsScreen(innerPadding) }

        navigation(
            route = ScreenRoute.SettingsRoot.routeName,
            startDestination = ScreenRoute.SettingsHome.routeName
        ) {
            composable(ScreenRoute.SettingsHome.routeName) {
                SettingsHomeScreen(innerPadding, navController)
            }
            composable(ScreenRoute.SettingsTheme.routeName) {
                SettingsThemeScreen(innerPadding)
            }
            composable(ScreenRoute.SettingsListItemPreferences.routeName) {
                SettingsListItemPreferencesScreen(innerPadding)
            }
        }
    }
}
