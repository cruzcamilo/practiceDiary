package com.feature.home.ui

import com.feature.home.domain.model.EntryModel

sealed interface EntriesUiState {
    object Loading : EntriesUiState
    data class Error(val throwable: Throwable) : EntriesUiState
    data class Success(val diaryEntries: List<EntryModel>) : EntriesUiState
}