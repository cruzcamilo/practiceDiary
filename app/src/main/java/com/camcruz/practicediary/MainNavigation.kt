package com.camcruz.practicediary

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.core.common.models.Routes
import com.feature.diary.entry.navigation.entryScreen
import com.feature.home.ui.navigation.createEntryScreen
import com.feature.home.ui.navigation.homeScreen

@Composable
fun MainNavigation(
    navController: NavHostController = rememberNavController(),
) {
    NavHost(navController = navController, startDestination = Routes.Home.route ) {
        homeScreen(
            onNavigateToCreateEntry = { navController.navigate(Routes.CreateEntry.route) },
            onEntryClick = { navController.navigate(Routes.DetailEntry.createRoute(it)) }
        )
        createEntryScreen { navController.navigateUp() }
        entryScreen(
            onBackClick = { navController.popBackStack() }
        )
    }
}