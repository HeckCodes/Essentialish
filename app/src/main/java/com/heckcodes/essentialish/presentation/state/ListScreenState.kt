package com.heckcodes.essentialish.presentation.state

import com.heckcodes.essentialish.domain.model.ShoppingList
import com.heckcodes.essentialish.domain.util.ShoppingFilter
import com.heckcodes.essentialish.domain.util.SortOrder

/**
 * State holder for the shopping lists screen, including the list data,
 * filter, sort order, loading, and error state.
 */

/**
 * Holds the state for the main screen showing shopping lists.
 */
data class ListScreenState(
    val shoppingLists: List<ShoppingList> = emptyList(),
    val filter: ShoppingFilter = ShoppingFilter.Active,
    val sortOrder: SortOrder = SortOrder.Descending,
)