package com.feature.home.domain.usecase


import com.feature.home.domain.model.EntryModel
import com.feature.home.domain.repository.EntryRepository

class UpdateEntryUseCase(private val entryRepository: EntryRepository) {
    suspend operator fun invoke(entryModel: EntryModel) {
        entryRepository.update(entryModel)
    }
}