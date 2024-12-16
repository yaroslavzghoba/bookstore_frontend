package org.example.bookstore.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Represents the book that is transferred to the server in the request body.
 *
 * @param title A title of the book.
 * @param author An author of the book.
 * @param genre A genre of the book.
 * @param price A price of the book.
 * @param stockQuantity The quantity of these books in the stock.
 * @param supplierId An unique id of the books' supplier.
 */
@Serializable
data class BookRequest(
    @SerialName("title") val title: String,
    @SerialName("author") val author: String?,
    @SerialName("genre") val genre: String?,
    @SerialName("price") val price: Double,
    @SerialName("stock_quantity") val stockQuantity: Int,
    @SerialName("supplier_id") val supplierId: Long?,
)