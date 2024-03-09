package com.feature.diaryentry.di

import com.core.db.EntryDao
import com.feature.diary.entry.domain.repository.DetailEntryRepository
import com.feature.diary.entry.domain.usecase.GetEntryUseCase
import com.feature.diary_entry.data.DetailEntryRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DiaryEntryModule {
    @Provides
    fun provideGetEntriesUseCase(entryRepository: DetailEntryRepository): GetEntryUseCase = GetEntryUseCase(entryRepository)

    @Provides
    fun provideDetailRepository(entryDao: EntryDao): DetailEntryRepository = DetailEntryRepositoryImpl(entryDao)

}