package com.feature.home.data

import com.core.common.models.EntryModel
import com.feature.home.domain.repository.EntryRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Singleton

@Singleton
class HomeEntryRepositoryImpl(private val localDataSource: LocalDataSource): EntryRepository {

    override val entries: Flow<List<EntryModel>> = localDataSource.getEntries()

    override suspend fun add(taskModel: EntryModel) {
        localDataSource.addEntry(taskModel)
    }

    override suspend fun delete(taskModel: EntryModel) {
        localDataSource.deleteEntry(taskModel)
    }

    override suspend fun deleteEntries() {
        localDataSource.deleteAllEntries()
    }
}
