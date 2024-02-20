@file:OptIn(ExperimentalMaterial3Api::class)

package com.feature.home.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.core.common.models.EntryModel

@Composable
fun HomeScreen(homeViewModel: HomeViewModel) {

    val showDialog: Boolean by homeViewModel.showDialog.observeAsState(false)

    Scaffold(
        topBar = { MyTopBar() },
        floatingActionButton = { FabButton { homeViewModel.onFabPressed() } }
        ) { padding ->
        FirstTimeUserScreen(padding)
        AddDiaryDialog(
            show = showDialog,
            onDismiss = { homeViewModel.onDialogClose() },
            viewModel = homeViewModel
        )
    }
}

@Composable
fun DiaryEntry(padding: PaddingValues) {
    Box(
        Modifier
            .padding(padding)
            .fillMaxSize(),
    ) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 10.dp
            )

        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.LightGray)
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally

            ) {
                Text(text = "Titulo")
                Text(text = "Titulo 2")
            }
        }
    }
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

@Composable
fun AddDiaryDialog(
    show: Boolean,
    onDismiss: ()-> Unit,
    viewModel: HomeViewModel
) {
    var title by remember { mutableStateOf("") }
    var initTempo by remember { mutableStateOf("") }
    var targetTempo by remember { mutableStateOf("") }

    if (show) {
        Dialog(onDismissRequest = { onDismiss() }) {
            Column(
                Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "Add an Entry")
                Spacer()
                Text(text = "Title")
                TextField(
                    value = title,
                    onValueChange = { title = it },
                    singleLine = true,
                    maxLines = 1
                )
                Spacer()

                Text(text = "Init Tempo")
                TextField(
                    value = initTempo,
                    onValueChange = { initTempo = it },
                    singleLine = true,
                    maxLines = 1
                )
                Spacer()
                Text(text = "Target Tempo")
                TextField(
                    value = targetTempo,
                    onValueChange = { targetTempo = it },
                    singleLine = true,
                    maxLines = 1
                )
                Spacer()
                Button(
                    enabled = isAddEntryEnabled(title, initTempo, targetTempo),
                    onClick = {
                        viewModel.onAddEntry(
                            EntryModel(
                                title = title,
                                initTempo = initTempo.toInt(),
                                targetTempo = targetTempo.toInt()
                            )
                        )
                    },
                    modifier = Modifier.fillMaxWidth()) {
                    Text(text = "Añadir tarea")
                }
            }
        }
    }
}

fun isAddEntryEnabled(title: String, initTempo: String, targetTempo: String): Boolean {
    return title.isNotEmpty() && initTempo.isNotEmpty() && targetTempo.isNotEmpty()
}

@Composable
fun Spacer() {
    Spacer(modifier = Modifier.size(16.dp))
}