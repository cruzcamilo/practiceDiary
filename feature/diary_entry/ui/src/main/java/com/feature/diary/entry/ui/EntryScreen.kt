package com.feature.diary.entry.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun EntryScreen(id: String) {
    Box(modifier = Modifier.fillMaxSize()) {
        Text(text = "Entry Screen $id")
    }
}

@Composable
@Preview(showBackground = true)
fun EntryScreenPreview() {
    EntryScreen("1")
}