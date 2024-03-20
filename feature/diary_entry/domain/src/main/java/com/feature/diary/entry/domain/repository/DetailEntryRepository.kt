package com.feature.diary.entry.domain.repository

import com.core.common.models.EntryModel
import kotlinx.coroutines.flow.Flow

interface DetailEntryRepository {
    fun getEntry(id: String): Flow<EntryModel>
    suspend fun update(taskModel: EntryModel)
    suspend fun delete(taskModel: EntryModel)
}