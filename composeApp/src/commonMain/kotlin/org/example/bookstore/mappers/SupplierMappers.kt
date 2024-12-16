package org.example.bookstore.mappers

import org.example.bookstore.data.model.SupplierRequest
import org.example.bookstore.model.Supplier

/**
 * Converts an instance of the [SupplierRequest] class to an instance of the [Supplier] class.
 */
fun SupplierRequest.toSupplier(id: Long?) = Supplier(
    supplierId = id,
    supplierName = this.supplierName,
    contactInfo = this.contactInfo,
)

/**
 * Converts an instance of the [Supplier] class to an instance of the [SupplierRequest] class.
 */
fun Supplier.toSupplierRequest() = SupplierRequest(
    supplierName = this.supplierName,
    contactInfo = this.contactInfo,
)