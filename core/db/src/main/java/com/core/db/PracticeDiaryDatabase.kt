package com.core.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [EntryEntity::class], version = 1)
abstract class PracticeDiaryDatabase: RoomDatabase() {
    abstract fun entryDao() : EntryDao
}