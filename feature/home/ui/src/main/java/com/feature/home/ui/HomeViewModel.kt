package com.feature.home.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.core.common.models.EntryModel
import com.feature.home.domain.usecase.DeleteEntriesUseCase
import com.feature.home.domain.usecase.GetEntriesUseCase
import com.feature.home.ui.EntriesUiState.Success
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel@Inject constructor(
    getEntriesUseCase: GetEntriesUseCase,
    private val deleteEntriesUseCase: DeleteEntriesUseCase,
):ViewModel() {

    val uiState: StateFlow<EntriesUiState> = getEntriesUseCase().map(::Success)
        .catch { Error(it).message }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), EntriesUiState.Loading)

    fun deleteAll() {
        viewModelScope.launch {
            deleteEntriesUseCase.invoke()
        }
    }
}

sealed interface EntriesUiState {
    object Loading : EntriesUiState
    data class Error(val throwable: Throwable) : EntriesUiState
    data class Success(val diaryEntries: List<EntryModel>) : EntriesUiState
}
