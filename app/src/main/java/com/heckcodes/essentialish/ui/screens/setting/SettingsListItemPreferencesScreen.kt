package com.heckcodes.essentialish.ui.screens.setting

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.Sort
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.heckcodes.essentialish.domain.util.SortOrder
import com.heckcodes.essentialish.presentation.viewmodel.SettingScreenViewModel
import com.heckcodes.essentialish.presentation.viewmodel.activityViewModel
import com.heckcodes.essentialish.ui.components.GeneralSelectionDialog
import com.heckcodes.essentialish.ui.components.SelectionType

@Composable
fun SettingsListItemPreferencesScreen(innerPadding: PaddingValues) {
    val settingScreenViewModel: SettingScreenViewModel = activityViewModel()
    val state by settingScreenViewModel.state.collectAsState()
    val (showListSortDialog, setShowListSortDialog) = rememberSaveable { mutableStateOf(false) }
    val (showItemSortDialog, setShowItemSortDialog) = rememberSaveable { mutableStateOf(false) }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding),
        contentPadding = PaddingValues(horizontal = 8.dp),
    ) {
        item {
            ListItem(
                headlineContent = { Text("List Sort Order") },
                supportingContent = {
                    Text(
                        buildAnnotatedString {
                            append("Lists are sorted in ")
                            withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.primary)) {
                                append(state.listSortOrder.name)
                            }
                            append(" order")
                        }
                    )
                },
                leadingContent = {
                    Box(modifier = Modifier.fillMaxHeight(), contentAlignment = Alignment.Center) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Rounded.Sort,
                            contentDescription = "Sort Order Icon",
                        )
                    }
                },
                modifier = Modifier.clickable { setShowListSortDialog(true) }
            )
        }
        item {
            ListItem(
                headlineContent = { Text("Item Sort Order") },
                supportingContent = {
                    Text(
                        buildAnnotatedString {
                            append("Items are sorted in ")
                            withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.primary)) {
                                append(state.itemSortOrder.name)
                            }
                            append(" order")
                        }
                    )
                },
                leadingContent = {
                    Box(modifier = Modifier.fillMaxHeight(), contentAlignment = Alignment.Center) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Rounded.Sort,
                            contentDescription = "Sort Order Icon",
                        )
                    }
                },
                modifier = Modifier.clickable { setShowItemSortDialog(true) }
            )
        }
    }

    if (showListSortDialog) {
        GeneralSelectionDialog(
            title = "List Sort Order",
            description = "Sorts the lists in ascending or descending order.",
            current = state.listSortOrder,
            onConfirm = {
                settingScreenViewModel.setListSortOrder(it)
                setShowListSortDialog(false)
            },
            type = SelectionType.EnumType<SortOrder>(),
            onDismiss = { setShowListSortDialog(false) }
        )
    }

    if (showItemSortDialog) {
        GeneralSelectionDialog(
            title = "Item Sort Order",
            description = "Sorts the items in ascending or descending order.",
            current = state.itemSortOrder,
            onConfirm = {
                settingScreenViewModel.setItemSortOrder(it)
                setShowItemSortDialog(false)
            },
            type = SelectionType.EnumType<SortOrder>(),
            onDismiss = { setShowItemSortDialog(false) }
        )
    }
}