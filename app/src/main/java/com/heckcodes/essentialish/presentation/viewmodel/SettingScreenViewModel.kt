package com.heckcodes.essentialish.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.heckcodes.essentialish.data.local.database.DataStoreManager
import com.heckcodes.essentialish.domain.util.DarkTheme
import com.heckcodes.essentialish.domain.util.PaletteStyle
import com.heckcodes.essentialish.domain.util.SortOrder
import com.heckcodes.essentialish.presentation.state.SettingScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingScreenViewModel @Inject constructor(
    private val dataStoreManager: DataStoreManager
) : ViewModel() {
    private val _state = MutableStateFlow(SettingScreenState())
    val state: StateFlow<SettingScreenState> = _state.asStateFlow()

    init {
        observeSettings()
    }

    private fun observeSettings() {
        val flows = listOf(
            dataStoreManager.getContrastLevel().map { it as Any },
            dataStoreManager.getListSortOrder().map { it as Any },
            dataStoreManager.getItemSortOrder().map { it as Any },
            dataStoreManager.getDynamicColor().map { it as Any },
            dataStoreManager.getDarkTheme().map { it as Any },
            dataStoreManager.getPaletteStyle().map { it as Any },
            dataStoreManager.getKeyColor().map { it as Any },
            dataStoreManager.getUseSystemFont().map { it as Any }
        )
        combine(flows) { value ->
            SettingScreenState(
                contrastLevel = value[0] as Double,
                listSortOrder = value[1] as SortOrder,
                itemSortOrder = value[2] as SortOrder,
                dynamicColor = value[3] as Boolean,
                darkTheme = value[4] as DarkTheme,
                paletteStyle = value[5] as PaletteStyle,
                keyColor = value[6] as String,
                useSystemFont = value[7] as Boolean
            )
        }.distinctUntilChanged()
            .onEach { _state.value = it }
            .launchIn(viewModelScope)
    }

    fun setContrastLevel(level: Double) {
        viewModelScope.launch {
            dataStoreManager.setContrastLevel(level)
        }
    }

    fun setListSortOrder(order: SortOrder) {
        viewModelScope.launch {
            dataStoreManager.setListSortOrder(order)
        }
    }

    fun setItemSortOrder(order: SortOrder) {
        viewModelScope.launch {
            dataStoreManager.setItemSortOrder(order)
        }
    }

    fun setDynamicColor(enabled: Boolean) {
        viewModelScope.launch {
            dataStoreManager.setDynamicColor(enabled)
        }
    }

    fun setDarkTheme(theme: DarkTheme) {
        viewModelScope.launch {
            dataStoreManager.setDarkTheme(theme)
        }
    }

    fun setPaletteStyle(style: PaletteStyle) {
        viewModelScope.launch {
            dataStoreManager.setPaletteStyle(style)
        }
    }

    fun setKeyColor(color: String) {
        viewModelScope.launch {
            dataStoreManager.setKeyColor(color)
        }
    }

    fun setUseSystemFont(enabled: Boolean) {
        viewModelScope.launch {
            dataStoreManager.setUseSystemFont(enabled)
        }
    }

    fun resetSettings() {
        viewModelScope.launch {
            dataStoreManager.resetSettings()
        }
    }
}
