package org.example.bookstore.di

import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import org.example.bookstore.data.BookStorageImpl
import org.example.bookstore.data.SupplierStorageImpl
import org.example.bookstore.data.model.BookStorage
import org.example.bookstore.data.model.SupplierStorage
import org.example.bookstore.utils.Constants
import org.koin.dsl.module

val appModule = module {

    single<HttpClient> {
        HttpClient {
            expectSuccess = true
            install(ContentNegotiation) {
                json()
            }
            defaultRequest {
                url(Constants.BASE_URL)
                contentType(ContentType.Application.Json)
            }
        }
    }

    single<BookStorage> {
        BookStorageImpl(client = get())
    }

    single<SupplierStorage> {
        SupplierStorageImpl(client = get())
    }
}