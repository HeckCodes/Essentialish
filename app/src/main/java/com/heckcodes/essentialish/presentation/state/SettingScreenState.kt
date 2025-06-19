package com.heckcodes.essentialish.presentation.state

import com.heckcodes.essentialish.domain.util.DarkTheme
import com.heckcodes.essentialish.domain.util.PaletteStyle
import com.heckcodes.essentialish.domain.util.SortOrder

data class SettingScreenState(
    val contrastLevel: Double = 0.0,
    val listSortOrder: SortOrder = SortOrder.Descending,
    val itemSortOrder: SortOrder = SortOrder.Descending,
    val dynamicColor: Boolean = true,
    val darkTheme: DarkTheme = DarkTheme.System,
    val paletteStyle: PaletteStyle = PaletteStyle.TonalSpot,
    val keyColor: String = "#ff631977",
    val useSystemFont: Boolean = false
)
