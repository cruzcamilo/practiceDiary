package com.feature.home.di

import com.core.db.EntryDao
import com.core.db.PracticeDiaryDatabase
import com.feature.home.data.EntryRepositoryImpl
import com.feature.home.domain.repository.EntryRepository
import com.feature.home.domain.usecase.AddEntryUseCase
import com.feature.home.domain.usecase.GetEntriesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object HomeModule {

    @Provides
    fun provideAddEntryUseCase(entryRepository: EntryRepository): AddEntryUseCase = AddEntryUseCase(entryRepository)

    @Provides
    fun provideGetEntriesUseCase(entryRepository: EntryRepository): GetEntriesUseCase = GetEntriesUseCase(entryRepository)

    @Provides
    fun provideEntryRepository(entryDao: EntryDao): EntryRepository = EntryRepositoryImpl(entryDao)

    @Provides
    fun provideEntryDao(todoDatabase: PracticeDiaryDatabase): EntryDao = todoDatabase.entryDao()
}