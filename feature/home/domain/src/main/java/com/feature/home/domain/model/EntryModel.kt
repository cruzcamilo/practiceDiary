package com.feature.home.domain.model

data class EntryModel(
    val title: String,
    val initTempo: String,
    val targetTempo: String,
    val id: Int = System.currentTimeMillis().hashCode()
)