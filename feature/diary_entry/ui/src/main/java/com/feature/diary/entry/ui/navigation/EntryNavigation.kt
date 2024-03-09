package com.feature.diary.entry.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.core.common.models.Routes
import com.feature.diary.entry.ui.EntryScreen

fun NavGraphBuilder.entryScreen() {
    composable(
        route = Routes.DetailEntry.route,
        arguments = listOf(navArgument(Routes.DetailEntry.argument) { type = NavType.StringType })
    ) { backStackEntry ->
        EntryScreen(
            id = backStackEntry.arguments?.getString(Routes.DetailEntry.argument).orEmpty()
        )
    }
}