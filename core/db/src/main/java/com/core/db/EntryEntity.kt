package com.core.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class EntryEntity(
    @PrimaryKey
    val id: Int,
    var title: String,
    var initTempo: Int,
    var targetTempo: Int
)

