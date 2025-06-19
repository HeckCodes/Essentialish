package com.heckcodes.essentialish.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.RawQuery
import androidx.room.Update
import androidx.sqlite.db.SupportSQLiteQuery
import com.heckcodes.essentialish.data.local.entity.ShoppingListEntity
import kotlinx.coroutines.flow.Flow

/**
 * DAO for accessing shopping list data in the local database.
 */
@Dao
interface   ShoppingListDao {

    /**
     * Executes a raw query to retrieve shopping lists.
     */
    @RawQuery(observedEntities = [ShoppingListEntity::class])
    fun getShoppingListsRaw(query: SupportSQLiteQuery): Flow<List<ShoppingListEntity>>

    /**
     * Retrieves a shopping list by its ID.
     */
    @Query("SELECT * FROM shopping_lists WHERE id = :listId")
    fun getShoppingListById(listId: Long): Flow<ShoppingListEntity?>

    /**
     * Inserts a shopping list into the database.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertShoppingList(shoppingList: ShoppingListEntity): Long

    /**
     * Updates a shopping list in the database.
     */
    @Update
    suspend fun updateShoppingList(shoppingList: ShoppingListEntity)

    /**
     * Deletes a shopping list from the database.
     */
    @Delete
    suspend fun deleteShoppingList(shoppingList: ShoppingListEntity)
}