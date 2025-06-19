package com.heckcodes.essentialish.ui.screens

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Replay
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.heckcodes.essentialish.presentation.viewmodel.SettingScreenViewModel
import com.heckcodes.essentialish.presentation.viewmodel.TopAppBarViewModel
import com.heckcodes.essentialish.presentation.viewmodel.activityViewModel
import com.heckcodes.essentialish.ui.components.GeneralSelectionDialog
import com.heckcodes.essentialish.ui.components.SelectionType
import com.heckcodes.essentialish.ui.components.SettingsGroupListItem
import com.heckcodes.essentialish.ui.model.TopAppBarAction
import com.heckcodes.essentialish.ui.navigation.ScreenRoute
import com.heckcodes.essentialish.ui.util.settingsGroups

@Composable
fun SettingsHomeScreen(
    innerPadding: PaddingValues,
    navHostController: NavHostController
) {
    val settingScreenViewModel: SettingScreenViewModel = activityViewModel()

    val (showResetDialog, setShowResetDialog) = rememberSaveable { mutableStateOf(false) }

    val topAppBarViewModel: TopAppBarViewModel = activityViewModel()
    val navBackStackEntry by navHostController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    LaunchedEffect(currentRoute) {
        if (currentRoute == ScreenRoute.SettingsHome.routeName) {
            topAppBarViewModel.updateAppBar(
                title = "Settings",
                actions = listOf(
                    TopAppBarAction(
                        icon = Icons.Rounded.Replay,
                        contentDescription = "Reset Settings",
                        onClick = {
                            setShowResetDialog(true)
                        }
                    )
                )
            )
        }
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding),
        contentPadding = PaddingValues(horizontal = 8.dp),
    ) {
        items(settingsGroups) { group ->
            SettingsGroupListItem(
                group = group,
                onClick = { navHostController.navigate(group.route) }
            )
        }
    }

    if( showResetDialog ) {
        GeneralSelectionDialog(
            title = "Reset Settings to Default",
            description = "Are you sure you want to reset all settings to their default values? This action cannot be undone.",
            current = Unit,
            onConfirm = {
                settingScreenViewModel.resetSettings()
                setShowResetDialog(false)
            },
            type = SelectionType.AlertType<Unit>(),
            onDismiss = { setShowResetDialog(false) }
        )
    }
}