/**
 * ViewModel for managing the state and logic of the shopping lists screen.
 * Handles filtering, sorting, and loading of shopping lists.
 */
package com.heckcodes.essentialish.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.heckcodes.essentialish.data.local.database.DataStoreManager
import com.heckcodes.essentialish.domain.usecase.shoppinglist.GetActiveShoppingListsUseCase
import com.heckcodes.essentialish.domain.usecase.shoppinglist.GetCompletedShoppingListsUseCase
import com.heckcodes.essentialish.domain.util.ShoppingFilter
import com.heckcodes.essentialish.domain.util.SortOrder
import com.heckcodes.essentialish.presentation.state.ListScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListScreenViewModel @Inject constructor(
    private val dataStoreManager: DataStoreManager,
    private val getActiveShoppingLists: GetActiveShoppingListsUseCase,
    private val getCompletedShoppingLists: GetCompletedShoppingListsUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(ListScreenState())
    val state: StateFlow<ListScreenState> = _state.asStateFlow()

    init {
        observeSettings()
        loadShoppingLists()
    }

    /**
     * Loads shopping lists based on the current filter and sort order.
     */
    private fun loadShoppingLists() {
        viewModelScope.launch {
            val filter = _state.value.filter
            val sortOrder = _state.value.sortOrder
            val flow = when (filter) {
                ShoppingFilter.Active -> getActiveShoppingLists(sortOrder)
                ShoppingFilter.Completed -> getCompletedShoppingLists(sortOrder)
            }
            flow.collectLatest { lists ->
                _state.update { it.copy(shoppingLists = lists) }
            }
        }
    }

    /**
     * Observes settings changes and updates the state accordingly.
     */
    private fun observeSettings() {
        viewModelScope.launch {
            dataStoreManager.getListSortOrder().collectLatest { sortOrder ->
                _state.update { it.copy(sortOrder = sortOrder) }
                loadShoppingLists()
            }
        }
    }

    /**
     * Updates the filter for the shopping lists.
     */
    fun updateFilter(filter: ShoppingFilter) {
        _state.update { it.copy(filter = filter) }
        loadShoppingLists()
    }

    /**
     * Updates the sort order for the shopping lists.
     * Doesn't update the user's default preference directly.
     * @param sortOrder The new sort order to apply.
     */
    fun updateSortOrder(sortOrder: SortOrder) {
        _state.update { it.copy(sortOrder = sortOrder) }
        loadShoppingLists()
    }
}