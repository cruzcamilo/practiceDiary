package com.feature.home.data

import com.core.common.models.EntryModel
import com.core.db.EntryDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class LocalDataSourceImpl(private val entryDao: EntryDao): LocalDataSource {

    override fun getEntries(): Flow<List<EntryModel>> {
        return entryDao.getEntries().map { items ->
            items.map {
                it.toDomain()
            }
        }
    }

    override suspend fun addEntry(taskModel: EntryModel) {
        entryDao.addEntry(taskModel.toData())
    }

    override suspend fun deleteEntry(taskModel: EntryModel) {
        entryDao.deleteEntry(taskModel.toData())
    }

    override suspend fun deleteAllEntries() {
        entryDao.deleteAllEntries()
    }
}
