package com.heckcodes.essentialish.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.heckcodes.essentialish.presentation.viewmodel.TopAppBarViewModel
import com.heckcodes.essentialish.presentation.viewmodel.activityViewModel

@Composable
fun CustomTopAppBar() {
    val topAppBarViewModel: TopAppBarViewModel = activityViewModel()
    val state by topAppBarViewModel.topAppBarState.collectAsState()

    val insets = WindowInsets.statusBars.asPaddingValues()
    val colors = MaterialTheme.colorScheme
    val backgroundColor = colors.surface
    val contentColor = colors.onSurface

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(backgroundColor)
            .statusBarsPadding()
            .height(32.dp + insets.calculateTopPadding()),
        contentAlignment = Alignment.CenterStart
    ) {
        Text(
            text = state.title,
            style = MaterialTheme.typography.titleLarge.copy(color = contentColor),
            modifier = Modifier
                .padding(start = 16.dp)
        )
        if (state.actions.isNotEmpty()) {
            Row(
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .padding(end = 16.dp)
            ) {
                state.actions.forEach { action ->
                    IconButton(onClick = action.onClick) {
                        Icon(
                            imageVector = action.icon,
                            contentDescription = action.contentDescription
                        )
                    }
                }
            }
        }
    }
}
