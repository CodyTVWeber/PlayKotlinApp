package com.jetbrains.kmpapp.data

import HymnObject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class HymnRepository(
    private val hymnApi: HymnApi,
    private val hymnStorage: HymnStorage,
) {
    private val scope = CoroutineScope(SupervisorJob())

    fun initialize() {
        scope.launch {
            refresh()
        }
    }

    suspend fun refresh() {
        val newObjects = hymnApi.getData()
        hymnStorage.saveObjects(newObjects)
    }

    fun getObjects(): Flow<List<HymnObject>> {
        val objects = hymnStorage.getObjects()
        return objects
    }

    fun getObjectById(objectID: Int): Flow<HymnObject?> {
        val objectById = hymnStorage.getObjectById(objectID)
        return objectById
    }
}
