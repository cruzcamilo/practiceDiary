package com.feature.diary.entry.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.core.common.models.EntryModel
import com.feature.diary.entry.domain.usecase.GetEntryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EntryViewModel @Inject constructor(
    private val getEntryUseCase: GetEntryUseCase
): ViewModel() {
    private val _entry = MutableLiveData<EntryModel>()
    val entry: LiveData<EntryModel> = _entry

    fun getEntryDetails(id: String) {
        viewModelScope.launch {
            getEntryUseCase.invoke(id).collect {
                _entry.value = it
            }
        }
    }

    fun editEntry(id: Int) {
        viewModelScope.launch {
//            getEntryUseCase.invoke(id).collect {
//                _entry.value = it
//            }
        }
    }
}
