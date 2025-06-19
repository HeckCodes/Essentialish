package com.heckcodes.essentialish.ui.screens.setting

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Colorize
import androidx.compose.material.icons.rounded.DarkMode
import androidx.compose.material.icons.rounded.FormatColorFill
import androidx.compose.material.icons.rounded.InvertColors
import androidx.compose.material.icons.rounded.Palette
import androidx.compose.material.icons.rounded.TextFields
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.heckcodes.essentialish.domain.util.DarkTheme
import com.heckcodes.essentialish.domain.util.PaletteStyle
import com.heckcodes.essentialish.presentation.viewmodel.SettingScreenViewModel
import com.heckcodes.essentialish.presentation.viewmodel.activityViewModel
import com.heckcodes.essentialish.ui.components.GeneralSelectionDialog
import com.heckcodes.essentialish.ui.components.SelectionType
import kotlin.math.round

@Composable
fun SettingsThemeScreen(innerPadding: PaddingValues) {
    val settingScreenViewModel: SettingScreenViewModel = activityViewModel()
    val state by settingScreenViewModel.state.collectAsState()

    val (showContrastDialog, setShowContrastDialog) = rememberSaveable { mutableStateOf(false) }
    val (showDarkThemeDialog, setShowDarkThemeDialog) = rememberSaveable { mutableStateOf(false) }
    val (showPaletteStyleDialog, setShowPaletteStyleDialog) = rememberSaveable { mutableStateOf(false) }
    val (showColorPickerDialog, setShowColorPickerDialog) = rememberSaveable { mutableStateOf(false) }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding),
        contentPadding = PaddingValues(horizontal = 8.dp),
    ) {
        item {
            ListItem(
                headlineContent = { Text("Dynamic Color") },
                supportingContent = {
                    Text(
                        buildAnnotatedString {
                            append("Using ")
                            withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.primary)) {
                                append(if (state.dynamicColor) "wallpaper" else "custom")
                            }
                            append(" colors")
                        }
                    )
                },
                leadingContent = {
                    Icon(
                        imageVector = Icons.Rounded.FormatColorFill,
                        contentDescription = "Dynamic Color Icon",
                    )
                },
                trailingContent = {
                    Switch(
                        checked = state.dynamicColor,
                        onCheckedChange = { settingScreenViewModel.setDynamicColor(it) }
                    )
                }
            )
        }

        item {
            ListItem(
                headlineContent = { Text("Dark Theme") },
                supportingContent = {
                    Text(
                        buildAnnotatedString {
                            append("Current theme is ")
                            withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.primary)) {
                                append(state.darkTheme.name)
                            }
                        }
                    )
                },
                leadingContent = {
                    Icon(
                        imageVector = Icons.Rounded.DarkMode,
                        contentDescription = "Dark Theme Icon",
                    )
                },
                trailingContent = {
                    Switch(
                        checked = state.darkTheme == DarkTheme.Dark,
                        onCheckedChange = {
                            settingScreenViewModel.setDarkTheme(
                                if (state.darkTheme == DarkTheme.Dark) DarkTheme.Light else DarkTheme.Dark
                            )
                        }
                    )
                },
                modifier = Modifier
                    .clickable { setShowDarkThemeDialog(true) }
            )
        }

        item {
            ListItem(
                headlineContent = { Text("Key Color") },
                supportingContent = { Text("Current key color for the app") },
                leadingContent = {
                    Icon(
                        imageVector = Icons.Rounded.Colorize,
                        contentDescription = "Color Picker Icon"
                    )
                },
                modifier = Modifier.let { if (state.dynamicColor) it.alpha(0.4f) else it }
                    .clickable(enabled = !state.dynamicColor) { setShowColorPickerDialog(true) }
            )
        }

        item {
            ListItem(
                headlineContent = { Text("Palettes Style") },
                supportingContent = {
                    Text(
                        buildAnnotatedString {
                            append("Current palette style is ")
                            withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.primary)) {
                                append(state.paletteStyle.name)
                            }
                        }
                    )
                },
                leadingContent = {
                    Icon(
                        imageVector = Icons.Rounded.Palette,
                        contentDescription = "Color Palette Icon",
                    )
                },
                modifier = Modifier.let { if (state.dynamicColor) it.alpha(0.4f) else it }
                    .clickable(enabled = !state.dynamicColor) { setShowPaletteStyleDialog(true) }
            )
        }

        item {
            ListItem(
                headlineContent = { Text("Contrast Level") },
                supportingContent = {
                    Text(
                        buildAnnotatedString {
                            append("Current contrast level is ")
                            withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.primary)) {
                                append(round(state.contrastLevel * 10).toInt().toString())
                            }
                        }
                    )
                },
                leadingContent = {
                    Box(modifier = Modifier.fillMaxHeight(), contentAlignment = Alignment.Center) {
                        Icon(
                            imageVector = Icons.Rounded.InvertColors,
                            contentDescription = "Contrast Icon",
                        )
                    }
                },
                modifier = Modifier
                    .let { if (state.dynamicColor) it.alpha(0.4f) else it }
                    .clickable(enabled = !state.dynamicColor) { setShowContrastDialog(true) }
            )
        }

        item {
            ListItem(
                headlineContent = { Text("Font Style") },
                supportingContent = {
                    Text(
                        buildAnnotatedString {
                            append("Using ")
                            withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.primary)) {
                                append(if (state.useSystemFont) "system" else "custom")
                            }
                            append(" font")
                        }
                    )
                },
                leadingContent = {
                    Icon(
                        imageVector = Icons.Rounded.TextFields,
                        contentDescription = "System Font Icon",
                    )
                },
                trailingContent = {
                    Switch(
                        checked = state.useSystemFont,
                        onCheckedChange = { settingScreenViewModel.setUseSystemFont(it) }
                    )
                }
            )
        }
    }

    if (showContrastDialog) {
        GeneralSelectionDialog(
            title = "Contrast Level",
            description = "Select the contrast level for the app theme. This is only applied if dynamic color is disabled.",
            current = state.contrastLevel,
            onConfirm = {
                settingScreenViewModel.setContrastLevel(it)
                setShowContrastDialog(false)
            },
            type = SelectionType.DoubleType(
                min = 0.0,
                max = 1.0,
                step = 0.1
            ),
            onDismiss = { setShowContrastDialog(false) }
        )
    }

    if (showDarkThemeDialog) {
        GeneralSelectionDialog(
            title = "Dark Theme",
            description = "Select the dark theme for the app.",
            current = state.darkTheme,
            onConfirm = {
                settingScreenViewModel.setDarkTheme(it)
                setShowDarkThemeDialog(false)
            },
            type = SelectionType.EnumType<DarkTheme>(),
            onDismiss = { setShowDarkThemeDialog(false) }
        )
    }

    if (showPaletteStyleDialog) {
        GeneralSelectionDialog(
            title = "Palette Style",
            description = "Select the palette style for the app theme.",
            current = state.paletteStyle,
            onConfirm = {
                settingScreenViewModel.setPaletteStyle(it)
                setShowPaletteStyleDialog(false)
            },
            type = SelectionType.EnumType<PaletteStyle>(),
            onDismiss = { setShowPaletteStyleDialog(false) }
        )
    }

    if (showColorPickerDialog) {
        GeneralSelectionDialog(
            title = "Key Color",
            description = "Select the key color for the app theme. This is only applied if dynamic color is disabled.",
            current = state.keyColor,
            onConfirm = {
                settingScreenViewModel.setKeyColor(it)
                setShowColorPickerDialog(false)
            },
            type = SelectionType.ColorType(),
            onDismiss = { setShowColorPickerDialog(false) }
        )
    }
}
