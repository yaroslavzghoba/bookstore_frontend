package org.example.bookstore.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * The object that represents a book supplier.
 *
 * @param supplierId An unique identifier of the supplier.
 * @param supplierName A name of the supplier.
 * @param contactInfo A contact info of the supplier.
 */
@Serializable
data class Supplier(
    @SerialName("supplier_id") val supplierId: Long?,
    @SerialName("supplier_name") val supplierName: String,
    @SerialName("supplier_info") val contactInfo: String?
)