package com.heckcodes.essentialish.domain.usecase.shoppinglist

import com.heckcodes.essentialish.domain.model.ShoppingList
import com.heckcodes.essentialish.domain.repository.ShoppingListRepository
import com.heckcodes.essentialish.domain.util.SortOrder
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Use case for retrieving active (not completed) shopping lists.
 *
 * @property shoppingListRepository Repository for shopping list operations.
 */
class GetActiveShoppingListsUseCase @Inject constructor(
    private val shoppingListRepository: ShoppingListRepository
) {
    /**
     * Returns a flow of active shopping lists, sorted as specified.
     *
     * @param sortOrder The order in which to sort the results.
     * @return A flow emitting the list of active shopping lists.
     */
    operator fun invoke(sortOrder: SortOrder): Flow<List<ShoppingList>> {
        return shoppingListRepository.getShoppingLists(isCompleted = false, sortOrder)
    }
}
