package com.heckcodes.essentialish.domain.repository

import com.heckcodes.essentialish.domain.model.ShoppingList
import com.heckcodes.essentialish.domain.util.SortOrder
import kotlinx.coroutines.flow.Flow

/**
 * Repository interface for managing shopping lists.
 */
interface ShoppingListRepository {
    /**
     * Retrieves a list of shopping lists, optionally filtered by completion status and sorted.
     *
     * @param isCompleted Filter by completion status, or null for all.
     * @param sortOrder The order in which to sort the results.
     * @return A flow emitting the list of shopping lists.
     */
    fun getShoppingLists(isCompleted: Boolean?, sortOrder: SortOrder): Flow<List<ShoppingList>>

    /**
     * Retrieves a shopping list by its ID.
     *
     * @param listId The ID of the shopping list.
     * @return A flow emitting the shopping list, or null if not found.
     */
    fun getShoppingListById(listId: Long): Flow<ShoppingList?>

    /**
     * Inserts a new shopping list.
     *
     * @param shoppingList The shopping list to insert.
     * @return The ID of the inserted shopping list.
     */
    suspend fun insertShoppingList(shoppingList: ShoppingList): Long

    /**
     * Updates an existing shopping list.
     *
     * @param shoppingList The shopping list to update.
     */
    suspend fun updateShoppingList(shoppingList: ShoppingList)

    /**
     * Deletes a shopping list.
     *
     * @param shoppingList The shopping list to delete.
     */
    suspend fun deleteShoppingList(shoppingList: ShoppingList)
}
