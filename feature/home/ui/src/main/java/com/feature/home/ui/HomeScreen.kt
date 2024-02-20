@file:OptIn(ExperimentalMaterial3Api::class)

package com.feature.home.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp

@Composable
fun HomeScreen(
    onNavigateToCreateEntry: () -> Unit,
    homeViewModel: HomeViewModel
) {
    initializeViewModelValues(homeViewModel, onNavigateToCreateEntry)

    Scaffold(
        topBar = { MyTopBar() },
        floatingActionButton = { FabButton { homeViewModel.onFabPressed() } }
        ) { padding ->
        FirstTimeUserScreen(padding)
    }
}

private fun initializeViewModelValues(
    homeViewModel: HomeViewModel,
    onNavigateToCreateEntry: () -> Unit
) {
    homeViewModel.navigateToCreateEntry = onNavigateToCreateEntry
}

@Composable
private fun FirstTimeUserScreen(padding: PaddingValues) {
    Box(
        Modifier
            .padding(padding)
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Add a new entry to start tracking your practice",
            fontSize = 14.sp
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopBar() {
    TopAppBar(
        title = { Text(text = "Practice Diary", color = Color.White) },
        modifier = Modifier.background(Color.Blue),
//        colors = TopAppBarDefaults.topAppBarColors(containerColor = Purple40)
        colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Magenta)
    )
}

@Composable
fun FabButton(onClick: () -> Unit) {
    FloatingActionButton(
        onClick = { onClick() }
    ) {
        Icon(imageVector = Icons.Filled.Add, contentDescription = "FAB")
    }
}
