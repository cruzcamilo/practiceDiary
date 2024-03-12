package com.feature.diary.entry.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.core.common.models.EntryModel

@Composable
fun EntryRoute(
    id: String,
    viewModel: EntryViewModel = hiltViewModel(),
    onBackClick: () -> Unit,
) {
    viewModel.getEntryDetails(id)

    val entry by viewModel.entry.observeAsState()
    entry?.let {
        EntryScreen(
            entry = it,
            entryIntent = viewModel.entryIntent,
            isTextFieldEnabled = viewModel.isTextFieldEnabled,
            onEdit = { viewModel.editEntry() },
            onSave = { entryModel -> viewModel.saveEntry(entryModel) },
            onBackClick = onBackClick,
        )
    }
}

@Composable
fun EntryScreen(
    entry: EntryModel,
    entryIntent: EntryIntent,
    isTextFieldEnabled: Boolean,
    onEdit: () -> Unit,
    onSave: (EntryModel) -> Unit,
    onBackClick: () -> Unit,
) {
    var title by remember { mutableStateOf(entry.title) }
    var initTempo by remember { mutableStateOf(entry.initTempo) }
    var targetTempo by remember { mutableStateOf(entry.targetTempo) }

    Column(
        modifier =
            Modifier
                .fillMaxSize()
                .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            modifier = Modifier.padding(8.dp),
            fontSize = 24.sp,
            text = title,
        )
        EntryTextField(
            label = stringResource(R.string.title),
            value = title,
            isEnabled = isTextFieldEnabled,
            onValueChanged = { title = it },
        )

        EntryTextField(
            label = stringResource(R.string.init_tempo),
            value = initTempo,
            isEnabled = isTextFieldEnabled,
            onValueChanged = { initTempo = it },
        )

        EntryTextField(
            label = stringResource(R.string.target_tempo),
            value = targetTempo,
            isEnabled = isTextFieldEnabled,
            onValueChanged = { targetTempo = it },
        )

        if (entryIntent == EntryIntent.Edit) {
            Button(
                onClick = { onEdit() },
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text(text = "Edit")
            }
            Button(
                onClick = { onBackClick() },
                modifier = Modifier.fillMaxWidth(),
                colors =
                    ButtonDefaults.buttonColors(
                        containerColor = Color.White,
                        contentColor = Color.Black,
                    ),
                border = BorderStroke(width = 1.dp, color = Color.Gray),
            ) {
                Text(text = "Back")
            }
        } else {
            Button(
                onClick = { onSave(getEntry(entry, title, initTempo, targetTempo)) },
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text(text = "Save")
            }
        }
    }
}

private fun getEntry(
    entry: EntryModel,
    title: String,
    initTempo: String,
    targetTempo: String,
) = entry.copy(
    title = title,
    initTempo = initTempo,
    targetTempo = targetTempo,
)

@Composable
fun EntryTextField(
    label: String,
    value: String,
    isEnabled: Boolean,
    onValueChanged: (String) -> Unit,
) {
    OutlinedTextField(
        label = { Text(text = label) },
        value = value,
        onValueChange = { onValueChanged(it) },
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
        enabled = isEnabled,
    )
}

@Composable
@Preview(showBackground = true)
fun EntryScreenPreview() {
    EntryScreen(
        entry = EntryModel("Altitudes", "60", "120"),
        entryIntent = EntryIntent.Edit,
        isTextFieldEnabled = false,
        onEdit = {},
        onSave = {},
        onBackClick = {},
    )
}

sealed class EntryIntent {
    data object Edit : EntryIntent()

    data object Save : EntryIntent()
}
