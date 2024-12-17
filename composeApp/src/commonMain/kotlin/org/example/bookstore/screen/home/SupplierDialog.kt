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
fun SupplierDialog(
    supplierDialogState: SupplierDialogState,
    onStateChanged: (SupplierDialogState) -> Unit,
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
                    value = supplierDialogState.supplierName,
                    onValueChange = { onStateChanged(supplierDialogState.copy(supplierName = it)) },
                    label = { Text("Supplier name") }
                )
                TextField(
                    value = supplierDialogState.contactInfo,
                    onValueChange = { onStateChanged(supplierDialogState.copy(contactInfo = it)) },
                    label = { Text("Contact info") }
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