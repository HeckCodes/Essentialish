package com.heckcodes.essentialish.domain.usecase.shoppinglist

import com.heckcodes.essentialish.domain.model.ShoppingList
import com.heckcodes.essentialish.domain.repository.ShoppingListRepository
import javax.inject.Inject

/**
 * Use case for deleting a shopping list.
 *
 * @property shoppingListRepository Repository for shopping list operations.
 */
class DeleteShoppingListUseCase @Inject constructor(
    private val shoppingListRepository: ShoppingListRepository
) {
    /**
     * Deletes the specified shopping list.
     *
     * @param shoppingList The shopping list to delete.
     */
    suspend operator fun invoke(shoppingList: ShoppingList) {
        shoppingListRepository.deleteShoppingList(shoppingList)
    }
}
