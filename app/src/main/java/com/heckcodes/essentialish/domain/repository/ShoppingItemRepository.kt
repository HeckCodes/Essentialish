package com.heckcodes.essentialish.domain.repository

import com.heckcodes.essentialish.domain.model.ShoppingItem
import com.heckcodes.essentialish.domain.util.SortOrder
import kotlinx.coroutines.flow.Flow

/**
 * Repository interface for managing shopping items.
 */
interface ShoppingItemRepository {
    /**
     * Retrieves shopping items for a specific list, optionally filtered by completion status and sorted.
     *
     * @param listId The ID of the shopping list.
     * @param isCompleted Filter by completion status, or null for all.
     * @param sortOrder The order in which to sort the results.
     * @return A flow emitting the list of shopping items.
     */
    fun getShoppingItemsForList(listId: Long, isCompleted: Boolean?, sortOrder: SortOrder): Flow<List<ShoppingItem>>

    /**
     * Retrieves a shopping item by its ID.
     *
     * @param itemId The ID of the shopping item.
     * @return A flow emitting the shopping item, or null if not found.
     */
    fun getShoppingItemById(itemId: Long): Flow<ShoppingItem?>

    /**
     * Inserts a new shopping item.
     *
     * @param shoppingItem The shopping item to insert.
     * @return The ID of the inserted shopping item.
     */
    suspend fun insertShoppingItem(shoppingItem: ShoppingItem): Long

    /**
     * Updates an existing shopping item.
     *
     * @param shoppingItem The shopping item to update.
     */
    suspend fun updateShoppingItem(shoppingItem: ShoppingItem)

    /**
     * Deletes a shopping item.
     *
     * @param shoppingItem The shopping item to delete.
     */
    suspend fun deleteShoppingItem(shoppingItem: ShoppingItem)
}
