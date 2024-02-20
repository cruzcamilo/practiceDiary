package com.feature.home.ui.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.core.common.models.Routes
import com.feature.home.ui.CreateEntryScreen
import com.feature.home.ui.HomeScreen
import com.feature.home.ui.HomeViewModel

fun NavGraphBuilder.homeScreen(
    onNavigateToCreateEntry: () -> Unit
) {
    composable(Routes.Home.route) {
        val homeViewModel: HomeViewModel = hiltViewModel()
        HomeScreen(
            homeViewModel = homeViewModel,
            onNavigateToCreateEntry = { onNavigateToCreateEntry() }
        )
    }
}

fun NavGraphBuilder.createEntryScreen(
    onNavigateBack: () -> Unit
) {
    composable(Routes.CreateEntry.route) {
        val homeViewModel: HomeViewModel = hiltViewModel()
        CreateEntryScreen(
            homeViewModel = homeViewModel,
            onEntryCreated = onNavigateBack
        )
    }
}