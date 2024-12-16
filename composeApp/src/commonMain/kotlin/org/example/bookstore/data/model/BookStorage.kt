package org.example.bookstore.data.model

import org.example.bookstore.model.Book

/**
 * Defines methods for storing and reading books.
 */
interface BookStorage {

    /**
     * Get a book by its [id].
     *
     * @param id The unique identifier of the book by which the search is performed.
     * @return A book with the corresponding identifier or null if not found.
     */
    suspend fun getById(id: Long): Book?

    /**
     * Get all books from the storage.
     *
     * @return All books in the storage.
     */
    suspend fun getAll(): List<Book>

    /**
     * Try to insert a book into the storage.
     *
     * @param book A book to be inserted into the storage.
     * @return Inserted book.
     *
     * @throws NoSuchElementException If the supplier of the book is not found in the storage.
     */
    suspend fun insert(book: Book): Book

    /**
     * Try to update the supplier in the storage.
     *
     * @param book The book that must be updated.
     * @return Updated book.
     *
     * @throws IllegalArgumentException If the identifier of the passed book is null.
     * @throws NoSuchElementException If the supplier of the book is not found in the storage.
     */
    suspend fun update(book: Book): Book

    /**
     * Delete a book by its id from the storage.
     *
     * @param id An unique identifier of the book that must be deleted.
     */
    suspend fun deleteById(id: Long)
}