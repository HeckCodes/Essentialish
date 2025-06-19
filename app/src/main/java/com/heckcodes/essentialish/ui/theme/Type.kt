/**
 * Defines the typography styles and font families for the Essentialish app,
 * customizing Material 3 Typography with Noto Sans and Noto Serif fonts.
 */

package com.heckcodes.essentialish.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import com.heckcodes.essentialish.R

val bodyFontFamily = FontFamily(
    Font(R.font.noto_sans_regular, FontWeight.Normal, FontStyle.Normal),
    Font(R.font.noto_sans_bold, FontWeight.Bold, FontStyle.Normal),
    Font(R.font.noto_sans_italic, FontWeight.Normal, FontStyle.Italic),
)

val displayFontFamily = FontFamily(
    Font(R.font.noto_serif_regular, FontWeight.Normal, FontStyle.Normal),
    Font(R.font.noto_serif_bold, FontWeight.Bold, FontStyle.Normal),
    Font(R.font.noto_serif_italic, FontWeight.Normal, FontStyle.Italic),
)

val baseline = Typography()

val AppTypography = Typography(
    displayLarge = baseline.displayLarge.copy(fontFamily = displayFontFamily),
    displayMedium = baseline.displayMedium.copy(fontFamily = displayFontFamily),
    displaySmall = baseline.displaySmall.copy(fontFamily = displayFontFamily),
    headlineLarge = baseline.headlineLarge.copy(fontFamily = displayFontFamily),
    headlineMedium = baseline.headlineMedium.copy(fontFamily = displayFontFamily),
    headlineSmall = baseline.headlineSmall.copy(fontFamily = displayFontFamily),
    titleLarge = baseline.titleLarge.copy(fontFamily = displayFontFamily),
    titleMedium = baseline.titleMedium.copy(fontFamily = displayFontFamily),
    titleSmall = baseline.titleSmall.copy(fontFamily = displayFontFamily),
    bodyLarge = baseline.bodyLarge.copy(fontFamily = bodyFontFamily),
    bodyMedium = baseline.bodyMedium.copy(fontFamily = bodyFontFamily),
    bodySmall = baseline.bodySmall.copy(fontFamily = bodyFontFamily),
    labelLarge = baseline.labelLarge.copy(fontFamily = bodyFontFamily),
    labelMedium = baseline.labelMedium.copy(fontFamily = bodyFontFamily),
    labelSmall = baseline.labelSmall.copy(fontFamily = bodyFontFamily),
)