package com.feature.home.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.core.common.models.EntryModel
import com.feature.home.domain.usecase.AddEntryUseCase
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
    private val addEntryUseCase: AddEntryUseCase,
    getEntriesUseCase: GetEntriesUseCase
):ViewModel() {

    val uiState: StateFlow<EntriesUiState> = getEntriesUseCase().map(::Success)
        .catch { Error(it) }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), EntriesUiState.Loading)

    var navigateToCreateEntry: () -> Unit = {}
    var navigateBackToHome: () -> Unit = {}

    fun onFabPressed() {
        navigateToCreateEntry()
    }

    fun onAddEntry(entryModel: EntryModel) {
        viewModelScope.launch {
            addEntryUseCase(entryModel)
            navigateBackToHome()
        }
    }

    fun isAddEntryEnabled(title: String, initTempo: String, targetTempo: String): Boolean {
        return title.isNotEmpty() && initTempo.isNotEmpty() && targetTempo.isNotEmpty()
    }

}