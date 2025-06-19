package com.heckcodes.essentialish.ui.navigation

sealed class ScreenRoute(val routeName: String) {
    object Lists : ScreenRoute("lists")
    object ListDetails : ScreenRoute("lists/list_details?list_id={list_id}") {
        fun createRoute(listId: Long?) = "lists/list_details?list_id=${listId ?: ""}"
    }
    object Stats : ScreenRoute("stats")

    object SettingsRoot : ScreenRoute("settings")
    object SettingsHome : ScreenRoute("settings/home")
    object SettingsTheme : ScreenRoute("settings/theme")
    object SettingsListItemPreferences : ScreenRoute("settings/list_item_preferences")
}
