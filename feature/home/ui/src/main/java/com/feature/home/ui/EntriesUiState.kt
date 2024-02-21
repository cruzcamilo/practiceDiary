package com.feature.home.ui

import com.core.common.models.EntryModel

sealed interface EntriesUiState {
    object Loading : EntriesUiState
    data class Error(val throwable: Throwable) : EntriesUiState
    data class Success(val diaryEntries: List<EntryModel>) : EntriesUiState
}