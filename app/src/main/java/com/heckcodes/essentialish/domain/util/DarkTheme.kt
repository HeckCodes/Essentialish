package com.heckcodes.essentialish.domain.util

enum class DarkTheme {
    System,
    Light,
    Dark;

    fun isDark(): Boolean {
        return this == Dark
    }

    fun isLight(): Boolean {
        return this == Light
    }

    fun isSystem(): Boolean {
        return this == System
    }
}