package com.heckcodes.essentialish.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.heckcodes.essentialish.presentation.viewmodel.TopAppBarViewModel
import com.heckcodes.essentialish.presentation.viewmodel.activityViewModel
import com.heckcodes.essentialish.ui.navigation.ScreenRoute

@Composable
fun StatsScreen(
    innerPadding: PaddingValues,
    navHostController: NavHostController
) {

    val topAppBarViewModel: TopAppBarViewModel = activityViewModel()
    val navBackStackEntry by navHostController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    LaunchedEffect(currentRoute) {
        if (currentRoute == ScreenRoute.Stats.routeName) {
            topAppBarViewModel.updateAppBar(title = "Statistics")
        }
    }


    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Stats Screen")
    }
}
