package org.example.bookstore.screen.home

import org.example.bookstore.model.Book

data class BookDialogState(
    val title: String = "",
    val author: String = "",
    val genre: String = "",
    val price: Double = 0.0,
    val stockQuantity: Int = 0,
    val supplierId: Long? = 0,
)

fun BookDialogState.toBook(id: Long?) = Book(
    bookId = id,
    title = this.title,
    author = this.author,
    genre = this.genre,
    price = this.price,
    stockQuantity = this.stockQuantity,
    supplierId = this.supplierId,
)

fun Book.toBookDialogState() = BookDialogState(
    title = this.title,
    author = this.author ?: "",
    genre = this.genre ?: "",
    price = this.price,
    stockQuantity = this.stockQuantity,
    supplierId = this.supplierId
)
