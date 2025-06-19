package com.heckcodes.essentialish.ui.model

import androidx.compose.ui.graphics.vector.ImageVector

data class SettingsGroup(
    val icon: ImageVector,
    val title: String,
    val description: String,
    val route: String
)

