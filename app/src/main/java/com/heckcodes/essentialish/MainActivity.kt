package com.heckcodes.essentialish

import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.heckcodes.essentialish.domain.util.DarkTheme
import com.heckcodes.essentialish.presentation.viewmodel.SettingScreenViewModel
import com.heckcodes.essentialish.presentation.viewmodel.activityViewModel
import com.heckcodes.essentialish.ui.AppRoot
import com.heckcodes.essentialish.ui.theme.AppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val settingScreenViewModel: SettingScreenViewModel = activityViewModel()
            val settingState by settingScreenViewModel.state.collectAsState()

            val isDark = when (settingState.darkTheme) {
                DarkTheme.System -> isSystemInDarkTheme()
                DarkTheme.Light -> false
                DarkTheme.Dark -> true
            }

            LaunchedEffect(isDark) {
                when (isDark) {
                    false -> enableEdgeToEdge(
                        statusBarStyle = SystemBarStyle.light(Color.TRANSPARENT, Color.TRANSPARENT),
                        navigationBarStyle = SystemBarStyle.light(
                            Color.TRANSPARENT,
                            Color.TRANSPARENT
                        )
                    )

                    true -> enableEdgeToEdge(
                        statusBarStyle = SystemBarStyle.dark(Color.TRANSPARENT),
                        navigationBarStyle = SystemBarStyle.dark(Color.TRANSPARENT)
                    )
                }
            }

            AppTheme(
                isDark = isDark,
                keyColor = settingState.keyColor,
                dynamicColor = settingState.dynamicColor,
                contrastLevel = settingState.contrastLevel,
                paletteStyle = settingState.paletteStyle,
                useSystemFont = settingState.useSystemFont
            ) {
                AppRoot()
            }
        }
    }
}