package com.feature.home.di

import com.core.db.EntryDao
import com.core.db.PracticeDiaryDatabase
import com.feature.home.data.HomeEntryRepositoryImpl
import com.feature.home.data.LocalDataSource
import com.feature.home.data.LocalDataSourceImpl
import com.feature.home.domain.repository.EntryRepository
import com.feature.home.domain.usecase.AddEntryUseCase
import com.feature.home.domain.usecase.DeleteEntriesUseCase
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
    fun provideDeleteEntriesUseCase(entryRepository: EntryRepository): DeleteEntriesUseCase = DeleteEntriesUseCase(entryRepository)

    @Provides
    fun provideEntryRepository(localDataSource: LocalDataSource): EntryRepository = HomeEntryRepositoryImpl(localDataSource)

    @Provides
    fun provideLocalDataSource(entryDao: EntryDao): LocalDataSource = LocalDataSourceImpl(entryDao)

    @Provides
    fun provideEntryDao(todoDatabase: PracticeDiaryDatabase): EntryDao = todoDatabase.entryDao()
}