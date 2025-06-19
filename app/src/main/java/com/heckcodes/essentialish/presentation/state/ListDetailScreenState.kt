package com.heckcodes.essentialish.presentation.state

import com.heckcodes.essentialish.domain.model.ShoppingItem
import com.heckcodes.essentialish.domain.model.ShoppingList


data class ListDetailScreenState(
    val shoppingList: ShoppingList? = null,
    val shoppingItems: List<ShoppingItem> = emptyList(),
    val shoppingListNameTextFieldValue: String = "",
    val shoppingItemNameTextFieldValue: String = ""
)