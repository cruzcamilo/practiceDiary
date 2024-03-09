package com.feature.home.data

import com.core.db.EntryDao
import com.feature.home.domain.model.EntryModel
import com.feature.home.domain.repository.EntryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class EntryRepositoryImpl @Inject constructor(private val entryDao: EntryDao): EntryRepository {

    override val entries: Flow<List<EntryModel>> =
        entryDao.getEntries().map { items ->
            items.map {
                it.toDomain()
            }
        }

    override suspend fun add(taskModel: EntryModel) {
        entryDao.addEntry(taskModel.toData())
    }

    override suspend fun update(taskModel: EntryModel) {
        entryDao.updateEntry(taskModel.toData())
    }

    override suspend fun delete(taskModel: EntryModel) {
        entryDao.deleteEntry(taskModel.toData())
    }

    override suspend fun deleteEntries() {
        entryDao.deleteAllEntries()
    }
}