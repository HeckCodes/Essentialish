package com.heckcodes.essentialish.domain.usecase.shoppinglist

import com.heckcodes.essentialish.domain.model.ShoppingList
import com.heckcodes.essentialish.domain.repository.ShoppingListRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetShoppingListUseCase @Inject constructor(
    private val repository: ShoppingListRepository
) {
    operator fun invoke(listId: Long): Flow<ShoppingList?> {
        return repository.getShoppingListById(listId)
    }
}

