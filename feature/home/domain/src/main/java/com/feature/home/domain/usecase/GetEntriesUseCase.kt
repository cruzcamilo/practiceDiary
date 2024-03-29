package com.feature.home.domain.usecase

import com.core.common.models.EntryModel
import com.feature.home.domain.repository.EntryRepository
import kotlinx.coroutines.flow.Flow

class GetEntriesUseCase(private val entryRepository: EntryRepository) {
    operator fun invoke(): Flow<List<EntryModel>> = entryRepository.entries
}