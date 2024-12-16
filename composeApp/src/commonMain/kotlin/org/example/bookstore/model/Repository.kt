package org.example.bookstore.model

/**
 * Defines methods for storing and reading books and suppliers.
 */
interface Repository {

    /**
     * Get a book by its [id].
     *
     * @param id The unique identifier of the book by which the search is performed.
     * @return A book with the corresponding identifier or null if not found.
     */
    suspend fun getBookById(id: Long): Book?

    /**
     * Get all books from the storage.
     *
     * @return All books in the storage.
     */
    suspend fun getAllBooks(): List<Book>

    /**
     * Try to insert a book into the storage.
     *
     * @param book A book to be inserted into the storage.
     * @return Inserted book.
     *
     * @throws NoSuchElementException If the supplier of the book is not found in the storage.
     */
    suspend fun insertBook(book: Book): Book

    /**
     * Try to update the supplier in the storage.
     *
     * @param book The book that must be updated.
     * @return Updated book.
     *
     * @throws IllegalArgumentException If the identifier of the passed book is null.
     * @throws NoSuchElementException If the supplier of the book is not found in the storage.
     */
    suspend fun updateBook(book: Book): Book

    /**
     * Delete all books from the storage.
     */
    suspend fun deleteAllBooks()

    /**
     * Delete a book by its id from the storage.
     *
     * @param id An unique identifier of the book that must be deleted.
     */
    suspend fun deleteBookById(id: Long)

    /**
     * Get a supplier by its [id].
     *
     * @param id The unique identifier of the supplier by which the search is performed.
     * @return A supplier with the corresponding identifier or null if not found.
     */
    suspend fun getSupplierById(id: Long): Supplier?

    /**
     * Get all suppliers in the storage.
     *
     * @return A all suppliers in the storage.
     */
    suspend fun getAllSuppliers(): List<Supplier>

    /**
     * Try to insert a supplier into the storage.
     *
     * @param supplier A supplier to be inserted into the storage.
     * @return Inserted supplier.
     */
    suspend fun insertSupplier(supplier: Supplier): Supplier

    /**
     * Try to update the supplier in the storage.
     *
     * @param supplier The supplier that must be updated.
     * @return Updated supplier.
     *
     * @throws IllegalArgumentException If the identifier of the passed supplier is null.
     */
    suspend fun updateSupplier(supplier: Supplier): Supplier

    /**
     * Delete all suppliers from the storage.
     */
    suspend fun deleteAllSuppliers()

    /**
     * Delete a supplier by its id from the storage.
     *
     * @param id An unique identifier of the supplier that must be deleted.
     */
    suspend fun deleteSupplierById(id: Long)
}