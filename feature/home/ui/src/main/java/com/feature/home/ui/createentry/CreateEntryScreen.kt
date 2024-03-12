package com.feature.home.ui.createentry

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.feature.home.ui.R

@Composable
fun CreateEntryRoute(
    createEntryViewModel: CreateEntryViewModel = hiltViewModel(),
    navigateBack: () -> Unit,
) {
    CreateEntryScreen(
        title = createEntryViewModel.title,
        onTitleChanged = { createEntryViewModel.onTitleChanged(it) },
        initTempo = createEntryViewModel.initTempo,
        onInitTempoChanged = { createEntryViewModel.onInitTempoChanged(it) },
        targetTempo = createEntryViewModel.targetTempo,
        onTargetTempoChanged = { createEntryViewModel.onTargetTempoChanged(it) },
        isButtonEnabled = createEntryViewModel.isButtonEnabled.value,
        onSubmit = { createEntryViewModel.onAddEntry() },
        navigateBack = navigateBack,
    )
}

@Composable
fun CreateEntryScreen(
    title: String,
    onTitleChanged: (String) -> Unit,
    initTempo: String,
    onInitTempoChanged: (String) -> Unit,
    targetTempo: String,
    onTargetTempoChanged: (String) -> Unit,
    isButtonEnabled: Boolean,
    onSubmit: () -> Unit,
    navigateBack: () -> Unit,
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Column(
            Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(start = 8.dp, end = 8.dp, bottom = 8.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                modifier = Modifier.padding(8.dp),
                fontSize = 24.sp,
                text = stringResource(R.string.add_an_entry),
            )

            Spacer()

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = title,
                onValueChange = { onTitleChanged(it) },
                singleLine = true,
                maxLines = 1,
                label = { Text(stringResource(R.string.title)) },
            )

            Spacer()

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                label = { Text(stringResource(R.string.init_tempo)) },
                value = initTempo,
                onValueChange = { onInitTempoChanged(it) },
                singleLine = true,
                maxLines = 1,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            )

            Spacer()

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                label = { Text(stringResource(R.string.target_tempo)) },
                value = targetTempo,
                onValueChange = { onTargetTempoChanged(it) },
                singleLine = true,
                maxLines = 1,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            )

            Spacer(16.dp)

            Button(
                enabled = isButtonEnabled,
                onClick = {
                    onSubmit()
                    navigateBack()
                },
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text(text = stringResource(R.string.submit))
            }
        }
    }
}

@Composable
fun Spacer(dp: Dp = 8.dp) = Spacer(modifier = Modifier.height(dp))

@Composable
@Preview
fun CreateEntryScreenPreview() {
    CreateEntryScreen(
        title = "Unnamed",
        onTitleChanged = {},
        initTempo = "90",
        onInitTempoChanged = {},
        targetTempo = "100",
        onTargetTempoChanged = {},
        isButtonEnabled = true,
        onSubmit = {},
        navigateBack = {},
    )
}
