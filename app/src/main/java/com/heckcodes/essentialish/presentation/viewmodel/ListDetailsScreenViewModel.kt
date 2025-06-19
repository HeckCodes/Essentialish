package com.heckcodes.essentialish.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.heckcodes.essentialish.domain.model.ShoppingItem
import com.heckcodes.essentialish.domain.model.ShoppingList
import com.heckcodes.essentialish.domain.usecase.shoppinglist.AddShoppingListUseCase
import com.heckcodes.essentialish.domain.usecase.shoppinglist.GetShoppingListUseCase
import com.heckcodes.essentialish.domain.usecase.shoppinglist.UpdateShoppingListUseCase
import com.heckcodes.essentialish.presentation.state.ListDetailScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListDetailsScreenViewModel @Inject constructor(
    private val addShoppingList: AddShoppingListUseCase,
    private val updateShoppingList: UpdateShoppingListUseCase,
    private val getShoppingList: GetShoppingListUseCase,
) : ViewModel() {
    private val _state = MutableStateFlow(ListDetailScreenState())
    val state: StateFlow<ListDetailScreenState> = _state.asStateFlow()

    fun resetAndLoadState(listId: Long?) {
        if(listId == null) {
            _state.value = ListDetailScreenState()
        } else {
            viewModelScope.launch {
                getShoppingList(listId).collect { shoppingList ->
                    _state.update { it.copy(shoppingList = shoppingList) }
                }
            }
        }
    }

    fun onShoppingListNameChange(newShoppingListName: String) {
        _state.update { it.copy(shoppingListNameTextFieldValue = newShoppingListName) }
    }

    fun addShoppingListToDB(): Long? {
        var listId: Long? = null
        val shoppingListName = _state.value.shoppingListNameTextFieldValue.trim()
        if (shoppingListName.isNotEmpty()) {
            viewModelScope.launch {
                listId = addShoppingList(shoppingListName)
            }
        }
        return listId;
    }

    fun updateShoppingListIcon(oldShoppingList: ShoppingList, newIconName: String) {
        viewModelScope.launch {
            updateShoppingList(oldShoppingList.copy(iconName = newIconName))
        }
    }
}