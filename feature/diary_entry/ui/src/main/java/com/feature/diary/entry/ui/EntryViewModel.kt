package com.feature.diary.entry.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.core.common.models.EntryModel
import com.core.common.models.Routes
import com.feature.diary.entry.domain.usecase.GetEntryUseCase
import com.feature.diary.entry.domain.usecase.UpdateEntryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EntryViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getEntryUseCase: GetEntryUseCase,
    private val updateEntryUseCase: UpdateEntryUseCase
): ViewModel() {
    private val routeId = savedStateHandle.get<String>(Routes.DetailEntry.argument)

    val uiState: StateFlow<EntryUiState> = getEntryUseCase(routeId.orEmpty()).map(EntryUiState::Success)
        .catch { Error(it).message }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), EntryUiState.Loading)

    var isTextFieldEnabled by mutableStateOf(false)
        private set

    var entryIntent by mutableStateOf<EntryIntent>(EntryIntent.Edit)

    fun editEntry() {
        updateFieldEnabled()
        entryIntent = EntryIntent.Save
    }

    fun saveEntry(entryModel: EntryModel) {
        viewModelScope.launch {
            updateEntryUseCase.invoke(entryModel)
        }
        updateFieldEnabled()
        entryIntent = EntryIntent.Edit
    }

    private fun updateFieldEnabled() {
        isTextFieldEnabled = !isTextFieldEnabled
    }
}

sealed interface EntryUiState {
    object Loading : EntryUiState
    data class Error(val throwable: Throwable) : EntryUiState
    data class Success(val entry: EntryModel) : EntryUiState
}

