@file:OptIn(ExperimentalMaterial3Api::class)

package com.feature.home.ui

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import com.feature.home.domain.model.EntryModel

@Composable
fun HomeScreen(
    onNavigateToCreateEntry: () -> Unit,
    homeViewModel: HomeViewModel
) {
    initializeViewModelValues(homeViewModel, onNavigateToCreateEntry)
    val lifecycle = LocalLifecycleOwner.current.lifecycle

    val uiState by produceState<EntriesUiState>(
        initialValue = EntriesUiState.Loading,
        key1 = lifecycle,
        key2 = homeViewModel
    ){
        lifecycle.repeatOnLifecycle(state = Lifecycle.State.STARTED){
            homeViewModel.uiState.collect{ value = it}
        }
    }

    Scaffold(
        topBar = { MyTopBar() },
        floatingActionButton = { FabButton { homeViewModel.onFabPressed() } }
        ) { padding ->
        when(uiState) {
            is EntriesUiState.Error -> { TODO() }
            EntriesUiState.Loading -> { CircularProgressIndicator() }
            is EntriesUiState.Success -> {
                val entries = (uiState as EntriesUiState.Success).diaryEntries
                if (entries.isEmpty()) {
                    NoEntriesScreen(padding)
                } else {
                    EntriesGridLayout(padding = padding, entries = entries)
                }
            }
        }
    }
}

private fun initializeViewModelValues(
    homeViewModel: HomeViewModel,
    onNavigateToCreateEntry: () -> Unit
) {
    homeViewModel.navigateToCreateEntry = onNavigateToCreateEntry
}

@Composable
private fun NoEntriesScreen(padding: PaddingValues) {
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

@Composable
private fun EntriesGridLayout(padding: PaddingValues, entries: List<EntryModel>) {
    Box(
        Modifier
            .padding(padding)
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Log.d("Home", "Title ${entries.first().title}")
        // Replace with lazy column and real layout
        entries.toString()
        Text(
            text = "You have ${entries.size} entries",
            fontSize = 14.sp
        )
    }
}

@Composable
fun MyTopBar() {
    TopAppBar(
        title = { Text(text = "Practice Diary", color = Color.White) },
        modifier = Modifier.background(Color.Blue),
//        colors = TopAppBarDefaults.topAppBarColors(containerColor = Purple40)
        colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Blue)
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
