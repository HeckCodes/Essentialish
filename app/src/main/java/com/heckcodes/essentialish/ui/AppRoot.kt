package com.heckcodes.essentialish.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AddShoppingCart
import androidx.compose.material.icons.rounded.QueryStats
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material.icons.rounded.ShoppingCart
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.heckcodes.essentialish.presentation.viewmodel.TopAppBarViewModel
import com.heckcodes.essentialish.presentation.viewmodel.activityViewModel
import com.heckcodes.essentialish.ui.components.CreateListDialog
import com.heckcodes.essentialish.ui.components.CustomTopAppBar
import com.heckcodes.essentialish.ui.navigation.AppNavGraph
import com.heckcodes.essentialish.ui.navigation.ScreenRoute

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppRoot() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val (showCreateListDialog, setShowCreateListDialog) = rememberSaveable { mutableStateOf(false) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { CustomTopAppBar() },
        bottomBar = {
            NavigationBar {
                val items = listOf(
                    ScreenRoute.Lists to Icons.Rounded.ShoppingCart,
                    ScreenRoute.Stats to Icons.Rounded.QueryStats,
                    ScreenRoute.SettingsRoot to Icons.Rounded.Settings
                )

                val tabRootToHome = mapOf(
                    ScreenRoute.Lists.routeName to ScreenRoute.Lists.routeName,
                    ScreenRoute.Stats.routeName to ScreenRoute.Stats.routeName,
                    ScreenRoute.SettingsRoot.routeName to ScreenRoute.SettingsHome.routeName
                )

                items.forEach { (screen, icon) ->
                    NavigationBarItem(
                        icon = {
                            Icon(
                                imageVector = icon,
                                contentDescription = screen.routeName
                            )
                        },
                        label = { Text(screen.routeName.replaceFirstChar { it.uppercase() }) },
                        selected = currentRoute?.startsWith(screen.routeName) == true,
                        onClick = {
                            val homeRoute = tabRootToHome[screen.routeName]
                            if (currentRoute?.startsWith(screen.routeName) == true && currentRoute != homeRoute) {
                                navController.navigate(homeRoute!!) {
                                    popUpTo(screen.routeName) { inclusive = false }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            } else if (currentRoute != screen.routeName && currentRoute != homeRoute) {
                                navController.navigate(homeRoute ?: screen.routeName) {
                                    popUpTo(navController.graph.startDestinationId) { saveState = true }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                        }
                    )
                }
            }

        },
        floatingActionButton = {
            if (currentRoute.equals(ScreenRoute.Lists.routeName)) {
                FloatingActionButton(
                    onClick = {
                        setShowCreateListDialog(true)
                    }
                ) {
                    Icon(
                        Icons.Rounded.AddShoppingCart,
                        contentDescription = "Add new list"
                    )
                }
            }
        }
    ) { innerPadding ->
        AppNavGraph(
            navController = navController,
            startDestination = ScreenRoute.Lists.routeName,
            innerPadding = innerPadding
        )

        if (showCreateListDialog) {
            CreateListDialog(
                onDismiss = { setShowCreateListDialog(false) },
                onConfirm = { setShowCreateListDialog(false) },
                onCancel = { setShowCreateListDialog(false) },
            )
        }
    }
}
