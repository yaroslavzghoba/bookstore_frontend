package org.example.bookstore.data

import org.example.bookstore.data.model.BookStorage
import org.example.bookstore.data.model.SupplierStorage
import org.example.bookstore.model.Book
import org.example.bookstore.model.Repository
import org.example.bookstore.model.Supplier

class RepositoryImpl(
    private val bookStorage: BookStorage,
    private val supplierStorage: SupplierStorage,
) : Repository {

    override suspend fun getBookById(id: Long): Book? {
        return bookStorage.getById(id = id)
    }

    override suspend fun getAllBooks(): List<Book> {
        return bookStorage.getAll()
    }

    override suspend fun insertBook(book: Book): Book {
        return bookStorage.insert(book = book)
    }

    override suspend fun updateBook(book: Book): Book {
        return bookStorage.update(book = book)
    }

    override suspend fun deleteBookById(id: Long) {
        bookStorage.deleteById(id = id)
    }

    override suspend fun getSupplierById(id: Long): Supplier? {
        return supplierStorage.getById(id = id)
    }

    override suspend fun getAllSuppliers(): List<Supplier> {
        return supplierStorage.getAll()
    }

    override suspend fun insertSupplier(supplier: Supplier): Supplier {
        return supplierStorage.insert(supplier = supplier)
    }

    override suspend fun updateSupplier(supplier: Supplier): Supplier {
        return supplierStorage.update(supplier = supplier)
    }

    override suspend fun deleteSupplierById(id: Long) {
        supplierStorage.deleteById(id = id)
    }
}