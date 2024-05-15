package com.jetbrains.kmpapp.data

import HymnObject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map

interface HymnStorage {
    suspend fun saveObjects(newObjects: List<HymnObject>)

    fun getObjectById(objectID: Int): Flow<HymnObject?>

    fun getObjects(): Flow<List<HymnObject>>
}

class InMemoryHymnStorage : HymnStorage {
    private val storedObjects = MutableStateFlow(emptyList<HymnObject>())

    override suspend fun saveObjects(newObjects: List<HymnObject>) {
        storedObjects.value = newObjects
    }

    override fun getObjectById(objectID: Int): Flow<HymnObject?> {
        return storedObjects.map { objects ->
            objects.find { it.objectID == objectID }
        }
    }

    override fun getObjects(): Flow<List<HymnObject>> = storedObjects
}
