package com.feature.home.ui.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.core.common.models.Routes
import com.feature.home.ui.HomeScreen
import com.feature.home.ui.HomeViewModel

fun NavGraphBuilder.homeScreen(
//    onNavigateToArticle: () -> Unit
) {
    composable(Routes.Home.routes) {
        val homeViewModel: HomeViewModel = hiltViewModel()
        HomeScreen(
            homeViewModel = homeViewModel
        )
    }
}