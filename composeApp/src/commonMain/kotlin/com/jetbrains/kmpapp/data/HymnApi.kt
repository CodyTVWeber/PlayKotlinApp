package com.jetbrains.kmpapp.data

import HymnObject
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.utils.io.CancellationException

interface HymnApi {
    suspend fun getData(): List<HymnObject>
}

class KtorHymnApi(private val client: HttpClient) : HymnApi {
    companion object {
        private const val API_URL =
            "https://raw.githubusercontent.com/CodyTVWeber/data/main/hymns.json"
    }

    override suspend fun getData(): List<HymnObject> {
        return try {
            client.get(API_URL).body()
        } catch (e: Exception) {
            if (e is CancellationException) throw e
            e.printStackTrace()

            emptyList()
        }
    }
}
