package com.feature.diary.entry.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.core.common.models.EntryModel
import com.feature.diary.entry.domain.usecase.GetEntryUseCase
import com.feature.diary.entry.domain.usecase.UpdateEntryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EntryViewModel @Inject constructor(
    private val getEntryUseCase: GetEntryUseCase,
    private val updateEntryUseCase: UpdateEntryUseCase
): ViewModel() {
    private var _entry = MutableLiveData<EntryModel>()
    val entry: LiveData<EntryModel> = _entry

    var isTextFieldEnabled by mutableStateOf(false)
        private set

    var entryIntent by mutableStateOf<EntryIntent>(EntryIntent.Edit)

    fun getEntryDetails(id: String) {
        viewModelScope.launch {
            getEntryUseCase.invoke(id).collect {
                _entry.value = it
            }
        }
    }

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
