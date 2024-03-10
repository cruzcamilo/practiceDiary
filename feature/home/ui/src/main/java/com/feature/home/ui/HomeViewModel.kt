package com.feature.home.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.core.common.EntriesUiState
import com.core.common.EntriesUiState.Success
import com.feature.home.domain.usecase.GetEntriesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class HomeViewModel@Inject constructor(
    getEntriesUseCase: GetEntriesUseCase
):ViewModel() {

    val uiState: StateFlow<EntriesUiState> = getEntriesUseCase().map(::Success)
        .catch { Error(it) }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), EntriesUiState.Loading)

}
