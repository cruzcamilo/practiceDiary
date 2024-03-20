package com.feature.diary.entry.domain.usecase

import com.core.common.models.EntryModel
import com.feature.diary.entry.domain.repository.DetailEntryRepository
import kotlinx.coroutines.flow.Flow

class GetEntryUseCase(private val detailEntryRepository: DetailEntryRepository) {
    operator fun invoke(entryId: String): Flow<EntryModel> = detailEntryRepository.getEntry(entryId)
}