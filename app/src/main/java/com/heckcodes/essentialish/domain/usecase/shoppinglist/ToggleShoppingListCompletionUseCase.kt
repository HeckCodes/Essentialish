package com.heckcodes.essentialish.domain.usecase.shoppinglist

import com.heckcodes.essentialish.domain.model.ShoppingList
import com.heckcodes.essentialish.domain.repository.ShoppingListRepository
import javax.inject.Inject

/**
 * Use case for toggling the completion status of a shopping list.
 *
 * @property shoppingListRepository Repository for shopping list operations.
 */
class ToggleShoppingListCompletionUseCase @Inject constructor(
    private val shoppingListRepository: ShoppingListRepository
) {
    /**
     * Toggles the completion status of the given shopping list and updates it.
     *
     * @param shoppingList The shopping list to toggle.
     */
    suspend operator fun invoke(shoppingList: ShoppingList) {
        val updatedList = shoppingList.copy(
            completed = !shoppingList.completed,
            updatedAt = System.currentTimeMillis()
        )
        shoppingListRepository.updateShoppingList(updatedList)
    }
}
