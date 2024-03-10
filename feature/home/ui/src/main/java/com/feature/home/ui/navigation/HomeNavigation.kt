package com.feature.home.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.core.common.models.Routes
import com.feature.home.ui.HomeRoute
import com.feature.home.ui.createentry.CreateEntryRoute

fun NavGraphBuilder.homeScreen(
    onNavigateToCreateEntry: () -> Unit,
    onEntryClick: (String) -> Unit,
) {
    composable(Routes.Home.route) {
        HomeRoute(
            navigateToCreateEntry = { onNavigateToCreateEntry() },
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