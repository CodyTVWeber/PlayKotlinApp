package com.jetbrains.kmpapp.di

import com.jetbrains.kmpapp.data.InMemoryHymnStorage

import com.jetbrains.kmpapp.data.KtorHymnApi
import com.jetbrains.kmpapp.data.HymnApi
import com.jetbrains.kmpapp.data.HymnRepository
import com.jetbrains.kmpapp.data.HymnStorage
import com.jetbrains.kmpapp.screens.detail.DetailScreenModel
import com.jetbrains.kmpapp.screens.list.ListScreenModel
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.context.startKoin
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val dataModule = module {
    single {
        val json = Json { ignoreUnknownKeys = true }
        HttpClient {
            install(ContentNegotiation) {
                // TODO Fix API so it serves application/json
                json(json, contentType = ContentType.Any)
            }
        }
    }

    single<HymnApi> { KtorHymnApi(get()) }
    single<HymnStorage> { InMemoryHymnStorage() }
    single {
        HymnRepository(get(), get()).apply {
            initialize()
        }
    }
}

val screenModelsModule = module {
    factoryOf(::ListScreenModel)
    factoryOf(::DetailScreenModel)
}

fun initKoin() {
    startKoin {
        modules(
            dataModule,
            screenModelsModule,
        )
    }
}
