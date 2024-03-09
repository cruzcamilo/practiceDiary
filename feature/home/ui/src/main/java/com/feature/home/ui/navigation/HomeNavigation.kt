package com.feature.home.ui.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.core.common.models.Routes
import com.feature.home.ui.HomeScreen
import com.feature.home.ui.HomeViewModel
import com.feature.home.ui.createentry.CreateEntryRoute

fun NavGraphBuilder.homeScreen(
    onNavigateToCreateEntry: () -> Unit,
    onEntryClick: (String) -> Unit,
) {
    composable(Routes.Home.route) {
        val homeViewModel: HomeViewModel = hiltViewModel()
        HomeScreen(
            homeViewModel = homeViewModel,
            onNavigateToCreateEntry = { onNavigateToCreateEntry() },
            onEntryClick = onEntryClick
        )
    }
}

fun NavGraphBuilder.createEntryScreen(
    navigateBack: () -> Unit
) {
    composable(Routes.CreateEntry.route) {
        CreateEntryRoute(
            navigateBack = navigateBack
        )
    }
}