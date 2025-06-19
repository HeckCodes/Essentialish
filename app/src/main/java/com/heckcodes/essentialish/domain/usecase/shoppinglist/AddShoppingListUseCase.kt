package com.heckcodes.essentialish.domain.usecase.shoppinglist

import com.heckcodes.essentialish.domain.model.ShoppingList
import com.heckcodes.essentialish.domain.repository.ShoppingListRepository
import javax.inject.Inject

/**
 * Use case for adding a new shopping list.
 *
 * @property shoppingListRepository Repository for shopping list operations.
 */
class AddShoppingListUseCase @Inject constructor(
    private val shoppingListRepository: ShoppingListRepository
) {
    /**
     * Adds a new shopping list with the given name.
     *
     * @param name The name of the new shopping list.
     * @return The ID of the inserted shopping list.
     */
    suspend operator fun invoke(name: String): Long {
        val newShoppingList = ShoppingList(
            name = name,
            completed = false,
            createdAt = System.currentTimeMillis(),
            updatedAt = System.currentTimeMillis()
        )
        return shoppingListRepository.insertShoppingList(newShoppingList)
    }
}
