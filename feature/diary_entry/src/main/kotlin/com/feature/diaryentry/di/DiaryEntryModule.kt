package com.feature.diaryentry.di

import com.core.db.EntryDao
import com.feature.diary.entry.domain.repository.DetailEntryRepository
import com.feature.diary.entry.domain.usecase.GetEntryUseCase
import com.feature.diary.entry.domain.usecase.UpdateEntryUseCase
import com.feature.diary.entry.data.DetailEntryRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DiaryEntryModule {
    @Provides
    fun provideGetEntriesUseCase(entryRepository: DetailEntryRepository): GetEntryUseCase =
        GetEntryUseCase(entryRepository)

    @Provides
    fun provideUpdateEntryUseCase(entryRepository: DetailEntryRepository): UpdateEntryUseCase =
        UpdateEntryUseCase(entryRepository)

    @Provides
    fun provideDetailRepository(entryDao: EntryDao): DetailEntryRepository =
        DetailEntryRepositoryImpl(entryDao)

}