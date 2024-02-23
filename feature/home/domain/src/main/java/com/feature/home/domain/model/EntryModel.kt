package com.feature.home.domain.model

data class EntryModel(
    val id: Int = System.currentTimeMillis().hashCode(),
    val title: String,
    val initTempo: Int,
    val targetTempo: Int
)
