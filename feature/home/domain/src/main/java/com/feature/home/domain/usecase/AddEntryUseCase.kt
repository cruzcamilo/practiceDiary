package com.feature.home.domain.usecase

import com.core.common.models.EntryModel
import com.core.db.di.EntryRepository
import javax.inject.Inject

class AddEntryUseCase @Inject constructor(private val entryRepository: EntryRepository) {
    suspend fun invoke(entryModel: EntryModel) {
        entryRepository.add(entryModel)
    }
}