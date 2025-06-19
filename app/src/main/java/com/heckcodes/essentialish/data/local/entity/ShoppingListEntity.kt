package com.heckcodes.essentialish.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Entity representing a shopping list in the local database.
 *
 * @property id Unique identifier for the shopping list.
 * @property name Name of the shopping list.
 * @property completed Whether the shopping list is completed.
 * @property createdAt Timestamp when the list was created.
 * @property updatedAt Timestamp when the list was last updated.
 */
@Entity(tableName = "shopping_lists")
data class ShoppingListEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val completed: Boolean = false,
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis(),
    val iconName: String = "Fastfood"
)
