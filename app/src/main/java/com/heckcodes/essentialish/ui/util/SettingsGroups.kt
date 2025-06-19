package com.heckcodes.essentialish.ui.util

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.Sort
import androidx.compose.material.icons.rounded.ColorLens
import com.heckcodes.essentialish.ui.model.SettingsGroup
import com.heckcodes.essentialish.ui.navigation.ScreenRoute

val settingsGroups = listOf(
    SettingsGroup(
        icon = Icons.Rounded.ColorLens,
        title = "Theme",
        description = "Dark mode, Color & Font settings",
        route = ScreenRoute.SettingsTheme.routeName
    ),
    SettingsGroup(
        icon = Icons.AutoMirrored.Rounded.Sort,
        title = "List & Item Preferences",
        description = "Sort order, item order",
        route = ScreenRoute.SettingsListItemPreferences.routeName
    )
)

