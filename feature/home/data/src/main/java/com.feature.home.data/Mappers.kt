package com.feature.home.data

import com.core.db.EntryEntity
import com.feature.home.domain.model.EntryModel

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