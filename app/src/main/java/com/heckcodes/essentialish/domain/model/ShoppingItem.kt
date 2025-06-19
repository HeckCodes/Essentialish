package com.heckcodes.essentialish.domain.model

/**
 * Domain model representing a shopping item.
 *
 * @property id Unique identifier for the shopping item.
 * @property shoppingListId Foreign key referencing the parent shopping list.
 * @property name Name of the shopping item.
 * @property completed Whether the item is completed.
 * @property createdAt Timestamp when the item was created.
 * @property updatedAt Timestamp when the item was last updated.
 */
data class ShoppingItem(
    val id: Long = 0,
    val shoppingListId: Long,
    val name: String,
    val completed: Boolean,
    val createdAt: Long,
    val updatedAt: Long
)

