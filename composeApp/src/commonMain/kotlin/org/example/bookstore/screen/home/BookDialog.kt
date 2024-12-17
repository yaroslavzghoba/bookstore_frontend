package org.example.bookstore.screen.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog

@Composable
fun BookDialog(
    bookDialogState: BookDialogState,
    onStateChanged: (BookDialogState) -> Unit,
    onConfirmRequest: () -> Unit,
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Dialog(onDismissRequest = onDismissRequest) {
        Card(modifier = modifier) {
            Column(
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalAlignment = Alignment.End,
            ) {
                TextField(
                    value = bookDialogState.title,
                    onValueChange = { onStateChanged(bookDialogState.copy(title = it)) },
                    label = { Text("Title") }
                )
                TextField(
                    value = bookDialogState.author,
                    onValueChange = { onStateChanged(bookDialogState.copy(author = it)) },
                    label = { Text("Author") }
                )
                TextField(
                    value = bookDialogState.genre,
                    onValueChange = { onStateChanged(bookDialogState.copy(genre = it)) },
                    label = { Text("Genre") }
                )
                TextField(
                    value = bookDialogState.price.toString(),
                    onValueChange = {
                        val price = it.toDoubleOrNull()
                        if (price != null) onStateChanged(bookDialogState.copy(price = price))
                    },
                    label = { Text("Price") }
                )
                TextField(
                    value = bookDialogState.stockQuantity.toString(),
                    onValueChange = {
                        val stockQuantity = it.toIntOrNull()
                        if (stockQuantity != null)
                            onStateChanged(bookDialogState.copy(stockQuantity = stockQuantity))
                    },
                    label = { Text("Stock quantity") }
                )
                TextField(
                    value = bookDialogState.supplierId.toString(),
                    onValueChange = {
                        val supplierId = it.toLongOrNull()
                        if (supplierId != null)
                            onStateChanged(bookDialogState.copy(supplierId = supplierId))
                    },
                    label = { Text("Supplier's id") }
                )
                Row {
                    OutlinedButton(onClick = onDismissRequest) {
                        Text(text = "Dismiss")
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Button(onClick = onConfirmRequest) {
                        Text(text = "Confirm")
                    }
                }
            }
        }
    }
}