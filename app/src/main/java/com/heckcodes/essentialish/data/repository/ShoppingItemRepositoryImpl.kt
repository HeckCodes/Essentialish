package com.heckcodes.essentialish.data.repository

import androidx.sqlite.db.SimpleSQLiteQuery
import com.heckcodes.essentialish.data.local.dao.ShoppingItemDao
import com.heckcodes.essentialish.data.util.toDomain
import com.heckcodes.essentialish.data.util.toEntity
import com.heckcodes.essentialish.domain.model.ShoppingItem
import com.heckcodes.essentialish.domain.repository.ShoppingItemRepository
import com.heckcodes.essentialish.domain.util.SortOrder
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * Implementation of [ShoppingItemRepository] that interacts with the local database.
 *
 * @property shoppingItemDao The DAO for shopping item operations.
 */
class ShoppingItemRepositoryImpl @Inject constructor(
    private val shoppingItemDao: ShoppingItemDao
) : ShoppingItemRepository {

    /**
     * Returns the SQL ORDER BY clause for the given [SortOrder].
     */
    private fun getOrderByClause(sortOrder: SortOrder) =
        if( sortOrder == SortOrder.Ascending) {
            "ORDER BY updatedAt ASC"
        } else {
            "ORDER BY updatedAt DESC"
        }

    /**
     * Returns the SQL WHERE clause for filtering by completion status.
     */
    private fun getWhereClause(listId: Long, isCompleted: Boolean?) {
        val whereClause = "WHERE shoppingListId = ?"
        when (isCompleted) {
            true -> "$whereClause AND completed = 1"
            false -> "$whereClause AND completed = 0"
            null -> whereClause
        }
    }

    /**
     * Retrieves shopping items for a specific list, optionally filtered by completion status and sorted.
     */
    override fun getShoppingItemsForList(listId: Long, isCompleted: Boolean?, sortOrder: SortOrder): Flow<List<ShoppingItem>> {
        val args = mutableListOf<Any>()
        args.add(listId)

        val orderByClause = getOrderByClause(sortOrder)
        val whereClause = getWhereClause(listId, isCompleted)
        val queryString = "SELECT * FROM shopping_items $whereClause $orderByClause"
        val query = SimpleSQLiteQuery(
            queryString,
            args.toTypedArray()
        )

        return shoppingItemDao.getShoppingItemsRaw(query).map { entities ->
            entities.map { it.toDomain() }
        }
    }

    /**
     * Retrieves a shopping item by its ID.
     */
    override fun getShoppingItemById(itemId: Long): Flow<ShoppingItem?> {
        return shoppingItemDao.getShoppingItemById(itemId).map { entity ->
            entity?.toDomain()
        }
    }

    /**
     * Inserts a new shopping item into the database.
     */
    override suspend fun insertShoppingItem(shoppingItem: ShoppingItem): Long {
        val entityToInsert = shoppingItem.copy(updatedAt = System.currentTimeMillis()).toEntity()
        return shoppingItemDao.insertShoppingItem(entityToInsert)
    }

    /**
     * Updates an existing shopping item in the database.
     */
    override suspend fun updateShoppingItem(shoppingItem: ShoppingItem) {
        val entityToUpdate = shoppingItem.copy(updatedAt = System.currentTimeMillis()).toEntity()
        shoppingItemDao.updateShoppingItem(entityToUpdate)
    }

    /**
     * Deletes a shopping item from the database.
     */
    override suspend fun deleteShoppingItem(shoppingItem: ShoppingItem) {
        shoppingItemDao.deleteShoppingItem(shoppingItem.toEntity())
    }
}