package org.example.bookstore.mappers

import org.example.bookstore.data.model.BookRequest
import org.example.bookstore.model.Book

/**
 * Converts an instance of the [BookRequest] class to an instance of the [Book] class.
 */
fun BookRequest.toBook(id: Long?) = Book(
    bookId = id,
    title = this.title,
    author = this.author,
    genre = this.genre,
    price = this.price,
    stockQuantity = this.stockQuantity,
    supplierId = this.supplierId,
)

/**
 * Converts an instance of the [Book] class to an instance of the [BookRequest] class.
 */
fun Book.toBookRequest() = BookRequest(
    title = this.title,
    author = this.author,
    genre = this.genre,
    price = this.price,
    stockQuantity = this.stockQuantity,
    supplierId = this.supplierId,
)