package com.core.common.models

data class EntryModel(
    val id: Int = System.currentTimeMillis().hashCode(),
    val title: String,
    val initTempo: Int,
    val targetTempo: Int
)
