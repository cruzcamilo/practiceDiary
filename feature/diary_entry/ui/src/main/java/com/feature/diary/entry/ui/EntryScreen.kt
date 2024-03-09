package com.feature.diary.entry.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.core.common.models.EntryModel

@Composable
fun EntryRoute(
    id: String,
    entryViewModel: EntryViewModel = hiltViewModel(),
){
    entryViewModel.getEntryDetails(id)

    val entry by entryViewModel.entry.observeAsState()
    entry?. let { entry ->
        EntryScreen(
            entry = entry,
            onEdit = { entryViewModel.editEntry(it) }
        )
    }
}

@Composable
fun EntryScreen(
    entry: EntryModel,
    onEdit: (Int) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier.padding(8.dp),
            fontSize = 24.sp,
            text = entry.title
        )
        OutlinedTextField(
            label = {
                    Text(text = stringResource(R.string.title))
            },
            value = entry.title,
            onValueChange = { },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            enabled = false
        )
        OutlinedTextField(
            label = {
                Text(text = stringResource(R.string.init_tempo))
            },
            value = entry.initTempo,
            onValueChange = { },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            enabled = false
        )
        OutlinedTextField(
            label = {
                Text(text = stringResource(R.string.target_tempo))
            },
            value = entry.targetTempo,
            onValueChange = { },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            enabled = false
        )
        Button(
            onClick = { onEdit(entry.id) },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text(text = "Edit")
        }
    }
}

@Composable
@Preview(showBackground = true)
fun EntryScreenPreview() {
    EntryScreen(EntryModel("Altitudes", "60", "120"), onEdit = {})
}