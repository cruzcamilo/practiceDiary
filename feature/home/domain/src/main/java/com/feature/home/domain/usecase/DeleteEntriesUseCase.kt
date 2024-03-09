package com.feature.home.domain.usecase


import com.feature.home.domain.repository.EntryRepository

class DeleteEntriesUseCase(private val entryRepository: EntryRepository) {
    suspend operator fun invoke() {
        entryRepository.deleteEntries()
    }
}