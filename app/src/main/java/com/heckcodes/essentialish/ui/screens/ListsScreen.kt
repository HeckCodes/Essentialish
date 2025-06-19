package com.heckcodes.essentialish.ui.screens

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.Sort
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.heckcodes.essentialish.domain.util.SortOrder
import com.heckcodes.essentialish.presentation.viewmodel.ListScreenViewModel
import com.heckcodes.essentialish.presentation.viewmodel.TopAppBarViewModel
import com.heckcodes.essentialish.presentation.viewmodel.activityViewModel
import com.heckcodes.essentialish.ui.components.GeneralSelectionDialog
import com.heckcodes.essentialish.ui.components.SelectionType
import com.heckcodes.essentialish.ui.components.ShoppingListCard
import com.heckcodes.essentialish.ui.model.TopAppBarAction

@Composable
fun ListsScreen(
    innerPadding: PaddingValues,
    viewModel: ListScreenViewModel = hiltViewModel()
) {
    val state = viewModel.state.collectAsState().value
    val topAppBarViewModel: TopAppBarViewModel = activityViewModel()

    val (showSortOptionsDialog, setShowSortOptionsDialog) = rememberSaveable { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        topAppBarViewModel.updateAppBar(
            title = "Essentialish",
            actions = listOf(
                TopAppBarAction(Icons.AutoMirrored.Rounded.Sort, "Sort Lists") {
                    setShowSortOptionsDialog(true)
                }
            )
        )
    }

    LazyColumn(
        modifier = Modifier.padding(innerPadding).fillMaxSize(),
        contentPadding = PaddingValues(horizontal = 16.dp),
    ) {
        items(state.shoppingLists) { shoppingList ->
            ShoppingListCard(shoppingList)
        }
    }

    if (showSortOptionsDialog) {
        GeneralSelectionDialog(
            title = "Sort List Order",
            description = "Select the order in which you want to sort your lists.",
            current = state.sortOrder,
            onConfirm = {
                viewModel.updateSortOrder(it)
                setShowSortOptionsDialog(false)
            },
            type = SelectionType.EnumType<SortOrder>(),
            onDismiss = { setShowSortOptionsDialog(false) }
        )
    }
}
