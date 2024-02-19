package com.example.practicediary

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.core.common.models.Routes
import com.feature.diary.entry.ui.navigation.entryScreen
import com.feature.home.ui.navigation.homeScreen

@Composable
fun MainNavigation(
    navController: NavHostController = rememberNavController(),
) {
    NavHost(navController = navController, startDestination = Routes.Home.routes ) {
        homeScreen()
        entryScreen()
    }

}