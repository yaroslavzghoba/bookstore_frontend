package org.example.bookstore.model

import bookstore.composeapp.generated.resources.Res
import bookstore.composeapp.generated.resources.book_entities_nav_bar_item
import bookstore.composeapp.generated.resources.book_icon
import bookstore.composeapp.generated.resources.supplier_entities_nav_bar_item
import bookstore.composeapp.generated.resources.supplier_icon
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource

enum class Entity(
    val title: StringResource,
    val icon: DrawableResource,
) {
    BOOK(
        title = Res.string.book_entities_nav_bar_item,
        icon = Res.drawable.book_icon,
    ),
    SUPPLIER(
        title = Res.string.supplier_entities_nav_bar_item,
        icon = Res.drawable.supplier_icon,
    )
}