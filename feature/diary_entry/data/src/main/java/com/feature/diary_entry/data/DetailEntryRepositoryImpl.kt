package com.feature.diary_entry.data

import com.core.common.models.EntryModel
import com.core.db.EntryDao
import com.feature.diary.entry.domain.repository.DetailEntryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DetailEntryRepositoryImpl(private val entryDao: EntryDao): DetailEntryRepository {

    override suspend fun getEntry(id: String): Flow<EntryModel> {
        return entryDao.getEntryById(Integer.valueOf(id)).map {
            it.toDomain()
        }
    }

    override suspend fun update(taskModel: EntryModel) {
        entryDao.updateEntry(taskModel.toData())
    }

    override suspend fun delete(taskModel: EntryModel) {
        entryDao.deleteEntry(taskModel.toData())
    }

}