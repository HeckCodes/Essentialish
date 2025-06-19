package com.heckcodes.essentialish.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Fastfood
import androidx.compose.material.icons.rounded.ShoppingCart
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import com.heckcodes.essentialish.domain.model.ShoppingList
import com.heckcodes.essentialish.presentation.viewmodel.ListDetailsScreenViewModel

fun getIconByName(name: String): ImageVector {
    return when (name) {
        "Fastfood" -> Icons.Rounded.Fastfood
        "ShoppingCart" -> Icons.Rounded.ShoppingCart
        else -> Icons.Rounded.Fastfood
    }
}

@Composable
fun IconPickerDialog(
    currentIcon: String,
    onIconSelected: (String) -> Unit,
    onDismiss: () -> Unit
) {
    val icons = listOf("Fastfood", "ShoppingCart")
    Dialog(onDismissRequest = onDismiss) {
        Row(modifier = Modifier.padding(24.dp)) {
            icons.forEach { iconName ->
                IconButton(onClick = { onIconSelected(iconName) }) {
                    Icon(
                        imageVector = getIconByName(iconName),
                        contentDescription = iconName,
                        tint = if (iconName == currentIcon) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface
                    )
                }
            }
        }
    }
}

@Composable
fun ShoppingListCard(
    shoppingList: ShoppingList,
    listDetailsScreenViewModel: ListDetailsScreenViewModel = hiltViewModel()
) {
    var showIconDialog by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .pointerInput(Unit) {
                detectHorizontalDragGestures { change, dragAmount ->
                    if (dragAmount > 50) {}
                    if (dragAmount < -50) {}
                }
            }
            .padding(bottom = 8.dp)
            .clip(CardDefaults.shape)
            .clickable { }
    ) {
        Row(
            modifier = Modifier.padding(8.dp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.Top
        ) {
            IconButton(
                onClick = { showIconDialog = true },
                modifier = Modifier.padding(end = 8.dp),
            ) {
                Icon(
                    imageVector = getIconByName(shoppingList.iconName),
                    contentDescription = null
                )
            }
            Text(
                text = shoppingList.name,
                style = MaterialTheme.typography.titleMedium
            )
        }
    }

    if (showIconDialog) {
        IconPickerDialog(
            currentIcon = shoppingList.iconName,
            onIconSelected = {
                listDetailsScreenViewModel.updateShoppingListIcon(shoppingList, it)
                showIconDialog = false
            },
            onDismiss = { showIconDialog = false }
        )
    }
}