package com.feature.home.data

import com.core.common.models.EntryModel
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {
    fun getEntries(): Flow<List<EntryModel>>
    suspend fun addEntry(taskModel: EntryModel)
    suspend fun deleteEntry(taskModel: EntryModel)
    suspend fun deleteAllEntries()
}
