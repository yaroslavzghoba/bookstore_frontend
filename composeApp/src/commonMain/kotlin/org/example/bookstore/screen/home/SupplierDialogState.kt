package org.example.bookstore.screen.home

import org.example.bookstore.model.Supplier

data class SupplierDialogState(
    val supplierName: String = "",
    val contactInfo: String = ""
)

fun SupplierDialogState.toSupplier(id: Long?) = Supplier(
    supplierId = id,
    supplierName = this.supplierName,
    contactInfo = this.contactInfo,
)

fun Supplier.toSupplierDialogState() = SupplierDialogState(
    supplierName = this.supplierName,
    contactInfo = this.contactInfo ?: "",
)