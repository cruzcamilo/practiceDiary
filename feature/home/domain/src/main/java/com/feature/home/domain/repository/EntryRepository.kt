package com.feature.home.domain.repository

import com.feature.home.domain.model.EntryModel
import kotlinx.coroutines.flow.Flow

interface EntryRepository {
    val entries: Flow<List<EntryModel>>
    suspend fun add(taskModel: EntryModel)
    suspend fun update(taskModel: EntryModel)
    suspend fun delete(taskModel: EntryModel)
    suspend fun deleteEntries()
}