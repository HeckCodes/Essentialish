package com.heckcodes.essentialish.domain.usecase.shoppinglist

import com.heckcodes.essentialish.domain.model.ShoppingList
import com.heckcodes.essentialish.domain.repository.ShoppingListRepository
import javax.inject.Inject

/**
 * Use case for updating a shopping list.
 *
 * @property shoppingListRepository Repository for shopping list operations.
 */
class UpdateShoppingListUseCase @Inject constructor(
    private val shoppingListRepository: ShoppingListRepository
) {
    /**
     * Updates the given shopping list, setting the updatedAt timestamp to the current time.
     *
     * @param shoppingList The shopping list to update.
     */
    suspend operator fun invoke(shoppingList: ShoppingList) {
        shoppingListRepository.updateShoppingList(shoppingList.copy(updatedAt = System.currentTimeMillis()))
    }
}
