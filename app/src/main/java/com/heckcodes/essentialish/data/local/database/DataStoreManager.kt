package com.heckcodes.essentialish.data.local.database

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.doublePreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.heckcodes.essentialish.domain.util.DarkTheme
import com.heckcodes.essentialish.domain.util.PaletteStyle
import com.heckcodes.essentialish.domain.util.SortOrder
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DataStoreManager(private val dataStore: DataStore<Preferences>) {
    companion object {
        private val CONTRAST_LEVEL_KEY = doublePreferencesKey("contrast_level")
        private val LIST_SORT_ORDER_KEY = stringPreferencesKey("list_sort_order")
        private val ITEM_SORT_ORDER_KEY = stringPreferencesKey("item_sort_order")
        private val DYNAMIC_COLOR_KEY = booleanPreferencesKey("dynamic_color")
        private val DARK_THEME_KEY = stringPreferencesKey("dark_theme")
        private val PALETTE_STYLE_KEY = stringPreferencesKey("palette_style")
        private val KEY_COLOR_KEY = stringPreferencesKey("key_color")
        private val USE_SYSTEM_FONT_KEY = booleanPreferencesKey("use_system_font")
    }

    suspend fun setContrastLevel(level: Double) {
        dataStore.edit { prefs ->
            prefs[CONTRAST_LEVEL_KEY] = level
        }
    }

    fun getContrastLevel(): Flow<Double> =
        dataStore.data.map { prefs ->
            prefs[CONTRAST_LEVEL_KEY] ?: 0.0
        }

    suspend fun setListSortOrder(order: SortOrder) {
        dataStore.edit { prefs ->
            prefs[LIST_SORT_ORDER_KEY] = order.name
        }
    }

    fun getListSortOrder(): Flow<SortOrder> =
        dataStore.data.map { prefs ->
            SortOrder.entries.find { it.name == prefs[LIST_SORT_ORDER_KEY] } ?: SortOrder.Descending
        }

    suspend fun setItemSortOrder(order: SortOrder) {
        dataStore.edit { prefs ->
            prefs[ITEM_SORT_ORDER_KEY] = order.name
        }
    }

    fun getItemSortOrder(): Flow<SortOrder> =
        dataStore.data.map { prefs ->
            SortOrder.entries.find { it.name == prefs[ITEM_SORT_ORDER_KEY] } ?: SortOrder.Descending
        }

    suspend fun setDynamicColor(enabled: Boolean) {
        dataStore.edit { prefs ->
            prefs[DYNAMIC_COLOR_KEY] = enabled
        }
    }

    fun getDynamicColor(): Flow<Boolean> =
        dataStore.data.map { prefs ->
            // Default to true if the key is null
            // Don't use `==` as it will give false if the key is not set
            prefs[DYNAMIC_COLOR_KEY] ?: true
        }

    suspend fun setDarkTheme(theme: DarkTheme) {
        dataStore.edit { prefs ->
            prefs[DARK_THEME_KEY] = theme.name
        }
    }

    fun getDarkTheme(): Flow<DarkTheme> =
        dataStore.data.map { prefs ->
            DarkTheme.entries.find { it.name == prefs[DARK_THEME_KEY] } ?: DarkTheme.System
        }

    suspend fun setPaletteStyle(style: PaletteStyle) {
        dataStore.edit { prefs ->
            prefs[PALETTE_STYLE_KEY] = style.name
        }
    }

    fun getPaletteStyle(): Flow<PaletteStyle> =
        dataStore.data.map { prefs ->
            PaletteStyle.entries.find { it.name == prefs[PALETTE_STYLE_KEY] } ?: PaletteStyle.TonalSpot
        }

    suspend fun setKeyColor(color: String) {
        dataStore.edit { prefs ->
            prefs[KEY_COLOR_KEY] = color
        }
    }

    fun getKeyColor(): Flow<String> =
        dataStore.data.map { prefs ->
            prefs[KEY_COLOR_KEY] ?: "#ff631977"
        }

    suspend fun setUseSystemFont(enabled: Boolean) {
        dataStore.edit { prefs ->
            prefs[USE_SYSTEM_FONT_KEY] = enabled
        }
    }

    fun getUseSystemFont(): Flow<Boolean> =
        dataStore.data.map { prefs ->
            prefs[USE_SYSTEM_FONT_KEY] ?: false
        }

    suspend fun resetSettings() {
        dataStore.edit { prefs ->
            prefs.clear()
        }
    }
}