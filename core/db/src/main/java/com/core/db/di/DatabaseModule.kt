package com.core.db.di

import android.content.Context
import androidx.room.Room
import com.core.db.EntryDao
import com.core.db.PracticeDiaryDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun providePracticeDiaryDatabase(@ApplicationContext appContext: Context): PracticeDiaryDatabase {
        return Room.databaseBuilder(
            appContext,
            PracticeDiaryDatabase::class.java,
            "PracticeDiaryDatabase"
        ).build()
    }
}