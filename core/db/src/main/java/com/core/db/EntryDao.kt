package com.core.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface EntryDao {
    @Query("SELECT * from EntryEntity")
    fun getEntries(): Flow<List<EntryEntity>>

    @Query("SELECT * FROM EntryEntity WHERE id = :entryId")
    fun getEntryById(entryId: Int): Flow<EntryEntity>

    @Insert
    suspend fun addEntry(entry: EntryEntity)

    @Update
    suspend fun updateEntry(entry: EntryEntity)

    @Delete
    suspend fun deleteEntry(entry: EntryEntity)

    @Query("DELETE FROM EntryEntity")
    suspend fun deleteAllEntries()
}