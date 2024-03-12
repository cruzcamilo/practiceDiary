package com.feature.home.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
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
                NoEntriesScreen()
            } else {
                var openAlertDialog by remember { mutableStateOf(false) }

                DeleteConfirmationAlertDialog(
                    isVisible = openAlertDialog,
                    onDismiss = { openAlertDialog = false },
                    onAccept = { openAlertDialog = false }
                )

                Scaffold(
                    topBar = {
                        NavigationBar {
                            Spacer(modifier = Modifier.weight(1f, true))
                            IconButton(
                                modifier = Modifier.align(Alignment.CenterVertically), 
                                onClick = { openAlertDialog = true }
                            ) {
                                Icon(imageVector = Icons.Outlined.Delete, contentDescription = "delete")
                            }
                        }
                    },
                    floatingActionButton = { FabButton { onFabPressed() } }
                ) { padding ->
                    EntriesGridLayout(
                        padding = padding,
                        entries = entries,
                        onEntryClick = onEntryClick,
                    )
                }
            }
        }
    }
}

@Composable
private fun NoEntriesScreen() {
    Box(
        Modifier
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
    padding: PaddingValues,
    entries: List<EntryModel>,
    onEntryClick: (String) -> Unit,
) {
    Box(modifier = Modifier
        .fillMaxWidth()
        .padding(padding)) {
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
fun DeleteConfirmationAlertDialog(
    isVisible: Boolean,
    onDismiss: () -> Unit,
    onAccept: () -> Unit,
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        if (isVisible) {
            AlertDialog(
                properties = DialogProperties(),
                onDismissRequest = {
                    onDismiss()
                },
                title = {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        text = "Warning"
                    )
                },
                text = {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        text = "Do you want to delete all entries?"
                    )
                },
                dismissButton = {
                    Button(
                        modifier = Modifier.padding(end = 48.dp),
                        onClick = { onDismiss() }
                    ) {
                        Text("Cancel")
                    }
                },
                confirmButton = {
                    Button(onClick = { onAccept() }) {
                        Text("Confirm")
                    }
                },
            )
        }
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

@Preview
@Composable
fun DeleteConfirmationAlertDialogPreview() {
    DeleteConfirmationAlertDialog(
        isVisible = true,
        onAccept = { },
        onDismiss = { },
    )
}
