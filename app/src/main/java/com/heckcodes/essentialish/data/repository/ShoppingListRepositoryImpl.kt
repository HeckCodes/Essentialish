package com.heckcodes.essentialish.data.repository

import androidx.sqlite.db.SimpleSQLiteQuery
import com.heckcodes.essentialish.data.local.dao.ShoppingListDao
import com.heckcodes.essentialish.data.util.toDomain
import com.heckcodes.essentialish.data.util.toEntity
import com.heckcodes.essentialish.domain.model.ShoppingList
import com.heckcodes.essentialish.domain.repository.ShoppingListRepository
import com.heckcodes.essentialish.domain.util.SortOrder
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * Implementation of [ShoppingListRepository] that interacts with the local database.
 *
 * @property shoppingListDao The DAO for shopping list operations.
 */
class ShoppingListRepositoryImpl @Inject constructor(
    private val shoppingListDao: ShoppingListDao
) : ShoppingListRepository {

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
    private fun getWhereClauseForCompletion(isCompleted: Boolean?) =
        when (isCompleted) {
            true -> "WHERE completed = 1"
            false -> "WHERE completed = 0"
            null -> ""
        }

    /**
     * Retrieves a list of shopping lists, optionally filtered by completion status and sorted.
     */
    override fun getShoppingLists(isCompleted: Boolean?, sortOrder: SortOrder): Flow<List<ShoppingList>> {
        val whereClause = getWhereClauseForCompletion(isCompleted)
        val orderByClause = getOrderByClause(sortOrder)

        val queryString = "SELECT * FROM shopping_lists $whereClause $orderByClause"

        val query = SimpleSQLiteQuery(queryString)
        return shoppingListDao.getShoppingListsRaw(query).map { entities ->
            entities.map { it.toDomain() }
        }
    }

    /**
     * Retrieves a shopping list by its ID.
     */
    override fun getShoppingListById(listId: Long): Flow<ShoppingList?> {
        return shoppingListDao.getShoppingListById(listId).map { entity ->
            entity?.toDomain()
        }
    }

    /**
     * Inserts a new shopping list into the database.
     */
    override suspend fun insertShoppingList(shoppingList: ShoppingList): Long {
        val entityToInsert = shoppingList.copy(updatedAt = System.currentTimeMillis()).toEntity()
        return shoppingListDao.insertShoppingList(entityToInsert)
    }

    /**
     * Updates an existing shopping list in the database.
     */
    override suspend fun updateShoppingList(shoppingList: ShoppingList) {
        val entityToUpdate = shoppingList.copy(updatedAt = System.currentTimeMillis()).toEntity()
        shoppingListDao.updateShoppingList(entityToUpdate)
    }

    /**
     * Deletes a shopping list from the database.
     */
    override suspend fun deleteShoppingList(shoppingList: ShoppingList) {
        shoppingListDao.deleteShoppingList(shoppingList.toEntity())
    }
}