package com.core.db.di

import com.core.db.EntryDao
import com.core.db.EntryEntity
import com.feature.home.domain.model.EntryModel
import com.feature.home.domain.repository.EntryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class EntryRepository @Inject constructor(private val entryDao: EntryDao): EntryRepository {

    override val entries: Flow<List<EntryModel>> =
        entryDao.getEntries().map { items ->
            items.map {
                EntryModel(
                    it.id,
                    it.title,
                    it.initTempo,
                    it.targetTempo
                )
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
}

fun EntryModel.toData() : EntryEntity {
    return EntryEntity(id = id, title = title, initTempo = initTempo, targetTempo = targetTempo)
}