package com.heckcodes.essentialish.di.module

import android.content.Context
import androidx.room.Room
import com.heckcodes.essentialish.data.local.dao.ShoppingItemDao
import com.heckcodes.essentialish.data.local.dao.ShoppingListDao
import com.heckcodes.essentialish.data.local.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Hilt module for providing database and DAO dependencies.
 */
@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    /**
     * Provides the [AppDatabase] instance.
     */
    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "essentialish_database"
        )
            .fallbackToDestructiveMigration(true)
            .build()
    }

    /**
     * Provides the [ShoppingListDao] instance.
     */
    @Provides
    @Singleton
    fun provideShoppingListDao(appDatabase: AppDatabase): ShoppingListDao {
        return appDatabase.shoppingListDao()
    }

    /**
     * Provides the [ShoppingItemDao] instance.
     */
    @Provides
    @Singleton
    fun provideShoppingItemDao(appDatabase: AppDatabase): ShoppingItemDao {
        return appDatabase.shoppingItemDao()
    }
}
