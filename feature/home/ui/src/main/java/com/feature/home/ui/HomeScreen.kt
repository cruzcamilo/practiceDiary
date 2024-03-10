package com.feature.home.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import com.core.common.EntriesUiState
import com.core.common.models.EntryModel

@Composable
fun HomeRoute(
    homeViewModel: HomeViewModel = hiltViewModel(),
    navigateToCreateEntry: () -> Unit,
    onEntryClick: (String) -> Unit,
) {
    val lifecycle = LocalLifecycleOwner.current.lifecycle

    val uiState by produceState<EntriesUiState>(
        initialValue = EntriesUiState.Loading,
        key1 = lifecycle,
        key2 = homeViewModel
    ) {
        lifecycle.repeatOnLifecycle(state = Lifecycle.State.STARTED) {
            homeViewModel.uiState.collect { value = it }
        }
    }

    HomeScreen(
        onFabPressed = navigateToCreateEntry,
        uiState = uiState,
        onEntryClick = onEntryClick
    )
}

@Composable
fun HomeScreen(
    onFabPressed: () -> Unit,
    uiState: EntriesUiState,
    onEntryClick: (String) -> Unit
) {
    Scaffold(
        floatingActionButton = { FabButton { onFabPressed() } }
    ) { padding ->
        when (uiState) {
            is EntriesUiState.Error -> {
                TODO()
            }

            EntriesUiState.Loading -> {
                CircularProgressIndicator()
            }

            is EntriesUiState.Success -> {
                val entries = uiState.diaryEntries
                if (entries.isEmpty()) {
                    NoEntriesScreen(padding)
                } else {
                    EntriesGridLayout(
                        entries = entries,
                        onEntryClick = onEntryClick,
                    )
                }
            }
        }
    }
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
            text = stringResource(R.string.empty_screen),
            fontSize = 14.sp
        )
    }
}

@Composable
private fun EntriesGridLayout(
    entries: List<EntryModel>,
    onEntryClick: (String) -> Unit,
) {
    Box(modifier = Modifier.fillMaxWidth()) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.align(Alignment.Center),
            contentPadding = PaddingValues(8.dp),
            content = {
                items(entries.size) {
                    DiaryEntryItem(entries[it], onEntryClick)
                }
            }
        )
    }
}

@Composable
fun FabButton(onClick: () -> Unit) {
    FloatingActionButton(
        onClick = { onClick() }
    ) {
        Icon(imageVector = Icons.Filled.Add, contentDescription = "FAB")
    }
}

@Composable
@Preview
fun HomeScreenPreview() {
    val diariesEntries = listOf(EntryModel("Hey you", "90", "1000"))

    HomeScreen(
        onFabPressed = { },
        uiState = EntriesUiState.Success(diariesEntries),
        onEntryClick = {}
    )
}
