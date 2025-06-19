package com.heckcodes.essentialish.data.util

import com.heckcodes.essentialish.data.local.entity.ShoppingItemEntity
import com.heckcodes.essentialish.data.local.entity.ShoppingListEntity
import com.heckcodes.essentialish.domain.model.ShoppingItem
import com.heckcodes.essentialish.domain.model.ShoppingList

/**
 * Maps a [ShoppingListEntity] to a domain [ShoppingList] model.
 */
fun ShoppingListEntity.toDomain(): ShoppingList {
    return ShoppingList(
        id = this.id,
        name = this.name,
        completed = this.completed,
        createdAt = this.createdAt,
        updatedAt = this.updatedAt,
        iconName = this.iconName
    )
}

/**
 * Maps a domain [ShoppingList] model to a [ShoppingListEntity].
 */
fun ShoppingList.toEntity(): ShoppingListEntity {
    return ShoppingListEntity(
        id = this.id,
        name = this.name,
        completed = this.completed,
        createdAt = this.createdAt,
        updatedAt = this.updatedAt,
        iconName = this.iconName
    )
}

/**
 * Maps a [ShoppingItemEntity] to a domain [ShoppingItem] model.
 */
fun ShoppingItemEntity.toDomain(): ShoppingItem {
    return ShoppingItem(
        id = this.id,
        shoppingListId = this.shoppingListId,
        name = this.name,
        completed = this.completed,
        createdAt = this.createdAt,
        updatedAt = this.updatedAt
    )
}

/**
 * Maps a domain [ShoppingItem] model to a [ShoppingItemEntity].
 */
fun ShoppingItem.toEntity(): ShoppingItemEntity {
    return ShoppingItemEntity(
        id = this.id,
        shoppingListId = this.shoppingListId,
        name = this.name,
        completed = this.completed,
        createdAt = this.createdAt,
        updatedAt = this.updatedAt
    )
}