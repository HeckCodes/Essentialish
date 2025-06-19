package com.heckcodes.essentialish.presentation.viewmodel

import android.content.Context
import android.content.ContextWrapper
import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel

fun Context.findActivity(): ComponentActivity? {
    var context = this
    while (context is ContextWrapper) {
        if (context is ComponentActivity) return context
        context = context.baseContext
    }
    return null
}


@Composable
inline fun <reified T : ViewModel> activityViewModel(): T {
    val activity = LocalContext.current.findActivity() ?: error("No ComponentActivity found in LocalContext")
    return hiltViewModel(activity)
}
