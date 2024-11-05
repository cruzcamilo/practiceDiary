package com.feature.diary.entry.data

import com.core.common.models.EntryModel
import com.core.db.EntryEntity

fun EntryModel.toData() = EntryEntity(
    id = id,
    title = title,
    initTempo = Integer.valueOf(initTempo),
    targetTempo = Integer.valueOf(targetTempo)
)

fun EntryEntity.toDomain() = EntryModel(
    id = id,
    title = title,
    initTempo = initTempo.toString(),
    targetTempo = targetTempo.toString()
)