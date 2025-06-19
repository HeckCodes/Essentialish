package com.heckcodes.essentialish.domain.model

/**
 * Domain model representing a shopping list.
 *
 * @property id Unique identifier for the shopping list.
 * @property name Name of the shopping list.
 * @property completed Whether the shopping list is completed.
 * @property createdAt Timestamp when the list was created.
 * @property updatedAt Timestamp when the list was last updated.
 */
data class ShoppingList(
    val id: Long = 0,
    val name: String,
    val completed: Boolean,
    val createdAt: Long,
    val updatedAt: Long,
    val iconName: String = "Fastfood"
)
