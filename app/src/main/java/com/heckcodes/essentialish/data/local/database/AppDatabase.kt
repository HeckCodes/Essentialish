package com.heckcodes.essentialish.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.heckcodes.essentialish.data.local.dao.ShoppingItemDao
import com.heckcodes.essentialish.data.local.dao.ShoppingListDao
import com.heckcodes.essentialish.data.local.entity.ShoppingItemEntity
import com.heckcodes.essentialish.data.local.entity.ShoppingListEntity

/**
 * The Room database for the application, containing shopping lists and items.
 */
@Database(
    entities = [ShoppingListEntity::class, ShoppingItemEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    /**
     * Provides access to shopping list DAO.
     */
    abstract fun shoppingListDao(): ShoppingListDao

    /**
     * Provides access to shopping item DAO.
     */
    abstract fun shoppingItemDao(): ShoppingItemDao
}
