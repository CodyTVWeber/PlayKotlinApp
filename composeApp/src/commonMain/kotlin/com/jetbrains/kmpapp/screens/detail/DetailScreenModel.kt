package com.jetbrains.kmpapp.screens.detail

import HymnObject
import cafe.adriel.voyager.core.model.ScreenModel
import com.jetbrains.kmpapp.data.HymnRepository
import kotlinx.coroutines.flow.Flow

class DetailScreenModel(private val hymnRepository: HymnRepository) : ScreenModel {
    fun getObject(objectID: Int): Flow<HymnObject?> =
        hymnRepository.getObjectById(objectID)
}
