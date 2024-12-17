package org.example.bookstore.data

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import org.example.bookstore.data.model.SupplierStorage
import org.example.bookstore.mappers.toSupplierRequest
import org.example.bookstore.model.Supplier

class SupplierStorageImpl(private val client: HttpClient) : SupplierStorage {

    override suspend fun getById(id: Long): Supplier? {
        return try {
            client.get("/suppliers/$id").body<Supplier>()
        } catch (exception: ClientRequestException) {
            null
        }
    }

    override suspend fun getAll(): List<Supplier> {
        return try {
            client.get("/suppliers").body<List<Supplier>>()
        } catch (exception: ClientRequestException) {
            emptyList()
        }
    }

    override suspend fun insert(supplier: Supplier): Supplier {
        return client.post("/suppliers") {
            setBody(supplier.toSupplierRequest())
        }.body<Supplier>()
    }

    override suspend fun update(supplier: Supplier): Supplier {
        return client.put("/suppliers/${supplier.supplierId}") {
            setBody(supplier.toSupplierRequest())
        }.body<Supplier>()
    }

    override suspend fun deleteById(id: Long) {
        client.delete("/suppliers/$id")
    }
}