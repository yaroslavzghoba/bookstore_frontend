package org.example.bookstore.data

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.RedirectResponseException
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.utils.io.InternalAPI
import org.example.bookstore.data.model.BookStorage
import org.example.bookstore.mappers.toBookRequest
import org.example.bookstore.model.Book

class BookStorageImpl(private val client: HttpClient) : BookStorage {

    override suspend fun getById(id: Long): Book? {
        return try {
            client.get("/books/$id").body<Book>()
        } catch (exception: ClientRequestException) {
            null
        }
    }

    override suspend fun getAll(): List<Book> {
        return try {
            client.get("/books").body<List<Book>>()
        } catch (exception: ClientRequestException) {
            emptyList()
        }
    }

    override suspend fun insert(book: Book): Book {
        return client.post("/books") {
            setBody(book.toBookRequest())
        }.body<Book>()
    }

    override suspend fun update(book: Book): Book {
        return client.put("/books/${book.bookId}") {
            setBody(book.toBookRequest())
        }.body<Book>()
    }

    override suspend fun deleteById(id: Long) {
        client.delete("/books/$id")
    }
}