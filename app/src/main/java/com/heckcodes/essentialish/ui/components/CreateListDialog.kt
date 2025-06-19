package com.heckcodes.essentialish.ui.components

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
import androidx.hilt.navigation.compose.hiltViewModel
import com.heckcodes.essentialish.presentation.viewmodel.ListDetailsScreenViewModel

@Composable
fun CreateListDialog(
    onDismiss: () -> Unit,
    onCancel: () -> Unit,
    onConfirm: () -> Unit,
    viewModel: ListDetailsScreenViewModel = hiltViewModel()
) {
    val localFocusManager = LocalFocusManager.current
    val shoppingListNameTextFieldFocusRequester = remember { FocusRequester() }
    val state by viewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.resetAndLoadState(null)
        shoppingListNameTextFieldFocusRequester.requestFocus()
    }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Create New List") },
        text = {
            OutlinedTextField(
                value = state.shoppingListNameTextFieldValue,
                onValueChange = { viewModel.onShoppingListNameChange(it) },
                label = { Text("Shopping List Name") },
                singleLine = true,
                modifier = Modifier.focusRequester(shoppingListNameTextFieldFocusRequester),
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.Words,
                    autoCorrectEnabled = true,
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Done,
                ),
                keyboardActions = KeyboardActions(
                    onDone = { localFocusManager.clearFocus() }
                )
            )
        },
        confirmButton = {
            Button(onClick = {
                viewModel.addShoppingListToDB()
                onConfirm()
            }) { Text("Create List") }
        },
        dismissButton = {
            TextButton(onClick = onCancel) { Text("Cancel") }
        },
        icon = null,
    )
}