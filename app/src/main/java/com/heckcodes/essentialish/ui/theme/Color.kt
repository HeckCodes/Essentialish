/**
 * Defines the color palette for the Essentialish app, including light, dark,
 * medium contrast, and high contrast color schemes for Material 3 theming.
 */
package com.heckcodes.essentialish.ui.theme


import androidx.compose.ui.graphics.Color
import kotlin.math.max

object ColorPickerOptions {

    val Red = Color(0xFFE53935)
    val Orange = Color(0xFFFB8C00)
    val Yellow = Color(0xFFFDD835)
    val Green = Color(0xFF43A047)
    val Blue = Color(0xFF1976D2)
    val Indigo = Color(0xFF3949AB)
    val Violet = Color(0xFF8E24AA)

    val Black = Color(0xFF000000)
    val DarkGray = Color(0xFF424242)
    val Gray = Color(0xFF9E9E9E)
    val LightGray = Color(0xFFEEEEEE)
    val White = Color(0xFFFFFFFF)

    fun generateShades(baseColor: Color, numShades: Int): List<Color> {
        if (numShades <= 0) return emptyList()
        if (numShades == 1) return listOf(baseColor)

        val shades = mutableListOf<Color>()
        val baseRed = baseColor.red
        val baseGreen = baseColor.green
        val baseBlue = baseColor.blue

        val minBlendFactor = 0.15f
        val maxBlendFactor = 0.85f

        for (i in 0 until numShades) {
            val t = i.toFloat() / (numShades - 1).toFloat()
            val effectiveBlend = minBlendFactor + t * (maxBlendFactor - minBlendFactor)

            val color = if (effectiveBlend < 0.5f) {
                val darkerFactor = (0.5f - effectiveBlend) * 2
                Color(
                    red = baseRed * (1 - darkerFactor),
                    green = baseGreen * (1 - darkerFactor),
                    blue = baseBlue * (1 - darkerFactor)
                )
            } else {
                val lighterFactor = (effectiveBlend - 0.5f) * 2
                Color(
                    red = baseRed + (1f - baseRed) * lighterFactor,
                    green = baseGreen + (1f - baseGreen) * lighterFactor,
                    blue = baseBlue + (1f - baseBlue) * lighterFactor
                )
            }
            shades.add(color.copy(alpha = 1f))
        }

        return shades
    }

    fun getAllSpectrumColors(numHuesPerColor: Int = 5): Map<String, List<Color>> {
        val effectiveNumHues = max(3, numHuesPerColor)

        return mapOf(
            "Red" to generateShades(Red, effectiveNumHues),
            "Orange" to generateShades(Orange, effectiveNumHues),
            "Yellow" to generateShades(Yellow, effectiveNumHues),
            "Green" to generateShades(Green, effectiveNumHues),
            "Blue" to generateShades(Blue, effectiveNumHues),
            "Indigo" to generateShades(Indigo, effectiveNumHues),
            "Violet" to generateShades(Violet, effectiveNumHues),
//            "Grayscale" to listOf(
//                DarkGray, Color(0xFF616161), Gray, Color(0xFFBDBDBD), LightGray
//            )
        )
    }
}