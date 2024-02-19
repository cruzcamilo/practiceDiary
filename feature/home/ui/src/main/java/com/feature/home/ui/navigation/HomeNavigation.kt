package com.feature.home.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.core.common.models.Routes
import com.feature.home.ui.HomeScreen

fun NavGraphBuilder.homeScreen(
//    onNavigateToArticle: () -> Unit
) {
    composable(Routes.Home.routes) {
        HomeScreen(
//            onNavigateToArticle = onNavigateToArticle
        )
    }
}