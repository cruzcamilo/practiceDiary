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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog

@Composable
@Preview
fun HomeScreen() {
    Scaffold(
        topBar = { MyTopBar() },
        floatingActionButton = { FabButton() }
        ) { padding ->
        FirstTimeUserScreen(padding)
//        DiaryEntry(padding)
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
fun FabButton() {
    FloatingActionButton(onClick = {
        // llamar al viewmodel para actualizar el valor de show dialog
    }) {
        Icon(imageVector = Icons.Filled.Add, contentDescription = "FAB")
    }
}

@Composable
fun AddDiaryEntry(show: Boolean, onDismiss: ()-> Unit) {
    var myTask by remember { mutableStateOf("") }
    if (show) {
        Dialog(onDismissRequest = { onDismiss() }) {
            Column(Modifier.fillMaxWidth()) {
                Text(text = "Add an Entry")
                Spacer(modifier = Modifier.size(16.dp))
                TextField(
                    value = myTask,
                    onValueChange = { myTask = it },
                    singleLine = true,
                    maxLines = 1
                )
                Spacer(modifier = Modifier.size(16.dp))
                Button(onClick = {
//                    onEntryAdded(myTask)
                    // llamar al viewModel para guardar la entry
                }, modifier = Modifier.fillMaxWidth()) {
                    Text(text = "Añadir tarea")
                }
            }
        }
    }
}