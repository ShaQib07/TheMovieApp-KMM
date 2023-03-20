package com.shakib.movieapp.di

import com.shakib.movieapp.data.ApiService
import com.shakib.movieapp.data.DBHelper
import com.shakib.movieapp.data.Repository
import com.shakib.movieapp.domain.UseCase
import com.shakib.movieapp.platform.HttpClientLogger
import io.ktor.client.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.module

expect val platformModule: Module

object Modules {
    val networkClient = module {
        single {
            HttpClient {
                defaultRequest {
                    url {
                        protocol = URLProtocol.HTTPS
                        host = "api.themoviedb.org"
                        path("3/movie/")
                        parameters.append(
                            name = "api_key",
                            value = "db0b624bf0d01d702c3dd9de13a73a79"
                        )
                    }
                    header(HttpHeaders.ContentType, ContentType.Application.Json)
                }
                install(Logging) {
                    logger = HttpClientLogger
                    level = LogLevel.ALL
                }
                install(ContentNegotiation) {
                    json(Json { isLenient = true; ignoreUnknownKeys = true })
                }
            }
        }
    }

    val services = module {
        single { ApiService(get()) }
        single { DBHelper(get()) }
    }

    val repositories = module {
        factory { Repository(get(), get()) }
    }

    val useCases = module {
        factory { UseCase(get()) }
    }
}

fun initKoin(
    appModule: Module = module { },
    networkModule: Module = Modules.networkClient,
    servicesModule: Module = Modules.services,
    repositoriesModule: Module = Modules.repositories,
    useCasesModule: Module = Modules.useCases,
    viewModelsModule: Module = module { },
): KoinApplication = startKoin {
    modules(
        appModule,
        networkModule,
        servicesModule,
        repositoriesModule,
        useCasesModule,
        viewModelsModule,
        platformModule
    )
}
