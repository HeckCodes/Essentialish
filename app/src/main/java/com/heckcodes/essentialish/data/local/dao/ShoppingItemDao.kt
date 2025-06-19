package com.heckcodes.essentialish.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.RawQuery
import androidx.room.Update
import androidx.sqlite.db.SupportSQLiteQuery
import com.heckcodes.essentialish.data.local.entity.ShoppingItemEntity
import kotlinx.coroutines.flow.Flow

/**
 * DAO for accessing shopping item data in the local database.
 */
@Dao
interface ShoppingItemDao {

    /**
     * Executes a raw query to retrieve shopping items.
     */
    @RawQuery(observedEntities = [ShoppingItemEntity::class])
    fun getShoppingItemsRaw(query: SupportSQLiteQuery): Flow<List<ShoppingItemEntity>>

    /**
     * Retrieves a shopping item by its ID.
     */
    @Query("SELECT * FROM shopping_items WHERE id = :itemId")
    fun getShoppingItemById(itemId: Long): Flow<ShoppingItemEntity?>

    /**
     * Inserts a shopping item into the database.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertShoppingItem(shoppingItem: ShoppingItemEntity): Long

    /**
     * Updates a shopping item in the database.
     */
    @Update
    suspend fun updateShoppingItem(shoppingItem: ShoppingItemEntity)

    /**
     * Deletes a shopping item from the database.
     */
    @Delete
    suspend fun deleteShoppingItem(shoppingItem: ShoppingItemEntity)
}