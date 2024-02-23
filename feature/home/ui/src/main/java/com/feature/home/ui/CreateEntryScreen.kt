package com.feature.home.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.feature.home.domain.model.EntryModel

@Composable
fun CreateEntryScreen(
    homeViewModel: HomeViewModel,
    onEntryCreated: () -> Unit
) {
    initializeViewModelValues(homeViewModel, onEntryCreated)
    Scaffold(
        topBar = { MyTopBar() },
        ) { padding ->
        Box(
            Modifier
                .padding(padding)
                .fillMaxSize(),

            contentAlignment = Alignment.Center
        ) {
            CreateEntry(homeViewModel)
        }
    }
}

private fun initializeViewModelValues(
    homeViewModel: HomeViewModel,
    onNavigateToCreateEntry: () -> Unit
) {
    homeViewModel.navigateBackToHome = onNavigateToCreateEntry
}

@Composable
fun CreateEntry(viewModel: HomeViewModel) {
    var title by remember { mutableStateOf("") }
    var initTempo by remember { mutableStateOf("") }
    var targetTempo by remember { mutableStateOf("") }
    val scrollState = rememberScrollState()
    Column(
        Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(start = 8.dp, end = 8.dp, bottom = 8.dp)
            .verticalScroll(scrollState),
    ) {
        Text(text = "Add an Entry")
        Spacer()
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = title,
            onValueChange = { title = it },
            singleLine = true,
            maxLines = 1,
            label = { Text("Title") }
        )
        Spacer()
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Init Tempo") },
            value = initTempo,
            onValueChange = { initTempo = it },
            singleLine = true,
            maxLines = 1,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        Spacer()
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Target Tempo") },
            value = targetTempo,
            onValueChange = { targetTempo = it },
            singleLine = true,
            maxLines = 1,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        Spacer()
        Button(
            enabled = viewModel.isAddEntryEnabled(title, initTempo, targetTempo),
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

@Composable
fun Spacer() {
    Spacer(modifier = Modifier.size(8.dp))
}

