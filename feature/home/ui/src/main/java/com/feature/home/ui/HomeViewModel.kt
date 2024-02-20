package com.feature.home.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.core.common.models.EntryModel
import com.feature.home.domain.usecase.AddEntryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel@Inject constructor(
    private val addEntryUseCase: AddEntryUseCase
):ViewModel() {

    private val _showDialog = MutableLiveData<Boolean>()
    val showDialog: LiveData<Boolean> = _showDialog

    fun onFabPressed() {
        _showDialog.value = true
    }

    fun onDialogClose() {
        _showDialog.value = false
    }

    fun onAddEntry(entryModel: EntryModel) {
        viewModelScope.launch {
            addEntryUseCase.invoke(entryModel)
            Log.d("ViewModel", "Adding Task")
        }
    }

}