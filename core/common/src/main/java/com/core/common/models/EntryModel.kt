package com.core.common.models

data class EntryModel(
    val title: String,
    val initTempo: String,
    val targetTempo: String,
    val id: Int = System.currentTimeMillis().hashCode()
)