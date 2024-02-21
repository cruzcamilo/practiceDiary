package com.feature.home.domain.usecase

import com.core.common.models.EntryModel
import com.core.db.di.EntryRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetEntriesUseCase @Inject constructor(private val entryRepository: EntryRepository) {
    operator fun invoke(): Flow<List<EntryModel>> = entryRepository.entries
}