package com.heckcodes.essentialish.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.heckcodes.essentialish.ui.model.SettingsGroup

@Composable
fun SettingsGroupListItem(
    group: SettingsGroup,
    onClick: () -> Unit
) {
    ListItem(
        headlineContent = { Text(group.title) },
        supportingContent = { Text(group.description) },
        leadingContent = {
            Icon(
                imageVector = group.icon,
                contentDescription = group.title
            )
        },
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
    )
}
