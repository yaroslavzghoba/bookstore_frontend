package org.example.bookstore.data.model

import org.example.bookstore.model.Supplier

/**
 * Defines methods for storing and reading suppliers.
 */
interface SupplierStorage {

    /**
     * Get a supplier by its [id].
     *
     * @param id The unique identifier of the supplier by which the search is performed.
     * @return A supplier with the corresponding identifier or null if not found.
     */
    suspend fun getById(id: Long): Supplier?

    /**
     * Get all suppliers in the storage.
     *
     * @return A all suppliers in the storage.
     */
    suspend fun getAll(): List<Supplier>

    /**
     * Try to insert a supplier into the storage.
     *
     * @param supplier A supplier to be inserted into the storage.
     * @return Inserted supplier.
     */
    suspend fun insert(supplier: Supplier): Supplier

    /**
     * Try to update the supplier in the storage.
     *
     * @param supplier The supplier that must be updated.
     * @return Updated supplier.
     *
     * @throws IllegalArgumentException If the identifier of the passed supplier is null.
     */
    suspend fun update(supplier: Supplier): Supplier

    /**
     * Delete a supplier by its id from the storage.
     *
     * @param id An unique identifier of the supplier that must be deleted.
     */
    suspend fun deleteById(id: Long)
}