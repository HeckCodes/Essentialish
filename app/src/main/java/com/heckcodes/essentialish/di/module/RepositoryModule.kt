package com.heckcodes.essentialish.di.module

import com.heckcodes.essentialish.data.repository.ShoppingItemRepositoryImpl
import com.heckcodes.essentialish.data.repository.ShoppingListRepositoryImpl
import com.heckcodes.essentialish.domain.repository.ShoppingItemRepository
import com.heckcodes.essentialish.domain.repository.ShoppingListRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Hilt module for providing repository implementations.
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    /**
     * Binds [ShoppingListRepositoryImpl] to [ShoppingListRepository].
     */
    @Binds
    @Singleton
    abstract fun bindShoppingListRepository(
        shoppingListRepositoryImpl: ShoppingListRepositoryImpl
    ): ShoppingListRepository

    /**
     * Binds [ShoppingItemRepositoryImpl] to [ShoppingItemRepository].
     */
    @Binds
    @Singleton
    abstract fun bindShoppingItemRepository(
        shoppingItemRepositoryImpl: ShoppingItemRepositoryImpl
    ): ShoppingItemRepository
}
