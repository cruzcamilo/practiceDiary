package com.feature.diary.entry.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.core.common.models.Routes
import com.feature.diary.entry.ui.EntryRoute

fun NavGraphBuilder.entryScreen(
    onBackClick: () -> Unit,
) {
    composable(
        route = Routes.DetailEntry.route,
        arguments = listOf(navArgument(Routes.DetailEntry.argument) { type = NavType.StringType })
    ) {
        EntryRoute(
            onBackClick = onBackClick
        )
    }
}