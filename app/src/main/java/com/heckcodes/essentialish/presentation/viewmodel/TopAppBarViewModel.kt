package com.heckcodes.essentialish.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.heckcodes.essentialish.ui.model.TopAppBarAction
import com.heckcodes.essentialish.ui.model.TopAppBarState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class TopAppBarViewModel @Inject constructor() : ViewModel() {
    private val _topAppBarState = MutableStateFlow(TopAppBarState())
    val topAppBarState: StateFlow<TopAppBarState> = _topAppBarState

    fun updateAppBar(title: String, actions: List<TopAppBarAction> = emptyList()) {
        Log.d("TopAppBarViewModel", "updateAppBar: $title")
        _topAppBarState.value = TopAppBarState(title, actions)
    }
}
