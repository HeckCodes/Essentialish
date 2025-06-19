package com.heckcodes.essentialish.ui.screens

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.heckcodes.essentialish.ui.components.SettingsGroupListItem
import com.heckcodes.essentialish.ui.util.settingsGroups

@Composable
fun SettingsHomeScreen(
    innerPadding: PaddingValues,
    navController: NavController
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding),
        contentPadding = PaddingValues(horizontal = 8.dp),
    ) {
        items(settingsGroups) { group ->
            SettingsGroupListItem(
                group = group,
                onClick = { navController.navigate(group.route) }
            )
        }
    }
}