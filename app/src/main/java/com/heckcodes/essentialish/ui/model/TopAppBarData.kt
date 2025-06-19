package com.heckcodes.essentialish.ui.model

import androidx.compose.ui.graphics.vector.ImageVector

/**
 * Model for configuring the top app bar.
 */
data class TopAppBarAction(
    val icon: ImageVector,
    val contentDescription: String,
    val onClick: () -> Unit
)

data class TopAppBarState(
    val title: String = "",
    val actions: List<TopAppBarAction> = emptyList()
)

