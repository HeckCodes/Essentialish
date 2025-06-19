package com.heckcodes.essentialish.data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

/**
 * Entity representing a shopping item in the local database.
 *
 * @property id Unique identifier for the shopping item.
 * @property shoppingListId Foreign key referencing the parent shopping list.
 * @property name Name of the shopping item.
 * @property completed Whether the item is completed.
 * @property createdAt Timestamp when the item was created.
 * @property updatedAt Timestamp when the item was last updated.
 */
@Entity(
    tableName = "shopping_items",
    foreignKeys = [
        ForeignKey(
            entity = ShoppingListEntity::class,
            parentColumns = ["id"],
            childColumns = ["shoppingListId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index(value = ["shoppingListId"])]
)
data class ShoppingItemEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val shoppingListId: Long,
    val name: String,
    val completed: Boolean = false,
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis()
)
