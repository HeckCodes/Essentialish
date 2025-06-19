package com.heckcodes.essentialish.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.heckcodes.essentialish.presentation.viewmodel.ListDetailsScreenViewModel

@Composable
fun ListDetailScreen(
    listId: Long?,
    innerPadding: PaddingValues,
    viewModel: ListDetailsScreenViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()

    val localFocusManager = LocalFocusManager.current
    val shoppingListNameTextFieldFocusRequester = remember { FocusRequester() }

    LaunchedEffect(Unit) {
        viewModel.resetAndLoadState(listId)
        if (listId == null) {
            shoppingListNameTextFieldFocusRequester.requestFocus()
        }
    }

    Column(
        modifier = Modifier
            .padding(innerPadding)
            .padding(horizontal = 16.dp)
            .fillMaxSize()
    ) {
        OutlinedTextField(
            value = state.shoppingListNameTextFieldValue,
            onValueChange = {
                viewModel.onShoppingListNameChange(it)
            },
            label = { Text("Shopping List Name") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Words,
                autoCorrectEnabled = true,
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    localFocusManager.clearFocus()
                }),
            modifier = Modifier
                .fillMaxWidth()
                .focusRequester(shoppingListNameTextFieldFocusRequester)
        )
    }
}