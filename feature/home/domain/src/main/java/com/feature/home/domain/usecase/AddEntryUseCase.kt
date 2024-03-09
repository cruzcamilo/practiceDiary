package com.feature.home.domain.usecase

import com.core.common.models.EntryModel
import com.feature.home.domain.repository.EntryRepository

class AddEntryUseCase(private val entryRepository: EntryRepository) {
    suspend operator fun invoke(entryModel: EntryModel) {
        entryRepository.add(entryModel)
    }
}