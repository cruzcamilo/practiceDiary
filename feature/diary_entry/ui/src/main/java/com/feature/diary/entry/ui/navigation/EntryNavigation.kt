package com.feature.diary.entry.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.core.common.models.Routes
import com.feature.diary.entry.ui.EntryScreen

fun NavGraphBuilder.entryScreen(
//    onNavigateToArticle: () -> Unit
) {
    composable(Routes.DetailEntry.routes) {
        EntryScreen(
//            onNavigateToArticle = onNavigateToArticle
        )
    }
}