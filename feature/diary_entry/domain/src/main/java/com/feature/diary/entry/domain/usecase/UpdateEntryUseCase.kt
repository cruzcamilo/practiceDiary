package com.feature.diary.entry.domain.usecase

import com.core.common.models.EntryModel
import com.feature.diary.entry.domain.repository.DetailEntryRepository

class UpdateEntryUseCase(private val detailEntryRepository: DetailEntryRepository) {
    suspend operator fun invoke(entryModel: EntryModel) {
        detailEntryRepository.update(entryModel)
    }
}