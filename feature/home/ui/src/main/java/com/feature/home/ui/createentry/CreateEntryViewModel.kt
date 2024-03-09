package com.feature.home.ui.createentry

import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.feature.home.domain.model.EntryModel
import com.feature.home.domain.usecase.AddEntryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateEntryViewModel@Inject constructor(
    private val addEntryUseCase: AddEntryUseCase,
): ViewModel() {
    var title by mutableStateOf("")
        private set
    var initTempo by mutableStateOf("")
        private set
    var targetTempo by mutableStateOf("")
        private set

    val isButtonEnabled = derivedStateOf { isAddEntryEnabled() }

    fun onTitleChanged(title:String) {
        this.title = title
    }

    fun onInitTempoChanged(initTempo:String) {
        this.initTempo = initTempo
    }

    fun onTargetTempoChanged(targetTempo:String) {
        this.targetTempo = targetTempo
    }

    private fun isAddEntryEnabled() =
        title.isNotEmpty() && initTempo.isNotEmpty() && targetTempo.isNotEmpty()

    fun onAddEntry() {
        viewModelScope.launch {
            addEntryUseCase(EntryModel(title, initTempo, targetTempo))
        }
    }

}