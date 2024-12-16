package org.example.bookstore.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Represents the supplier that is transferred to the server in the request body
 *
 * @param supplierName A name of the supplier.
 * @param contactInfo A contact info of the supplier.
 */
@Serializable
data class SupplierRequest(
    @SerialName("supplier_name") val supplierName: String,
    @SerialName("supplier_info") val contactInfo: String?
)