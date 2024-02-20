package com.feature.home.ui

import android.util.Log
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

    var navigateToCreateEntry: () -> Unit = {}
    var navigateBackToHome: () -> Unit = {}

    fun onFabPressed() {
        navigateToCreateEntry()
    }

    fun onAddEntry(entryModel: EntryModel) {
        viewModelScope.launch {
//            addEntryUseCase(entryModel)
            Log.d("ViewModel", "Adding Task")
            navigateBackToHome()
        }
    }

    fun isAddEntryEnabled(title: String, initTempo: String, targetTempo: String): Boolean {
        return title.isNotEmpty() && initTempo.isNotEmpty() && targetTempo.isNotEmpty()
    }

}