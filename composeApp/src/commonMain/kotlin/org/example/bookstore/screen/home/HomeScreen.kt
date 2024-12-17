package org.example.bookstore.screen.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.DropdownMenuState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ExposedDropdownMenuBox
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.ListItem
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import bookstore.composeapp.generated.resources.Res
import bookstore.composeapp.generated.resources.more_icon
import org.example.bookstore.model.Book
import org.example.bookstore.model.Entity
import org.example.bookstore.model.Supplier
import org.example.bookstore.ui.component.BookstoreNavigationRail
import org.example.bookstore.ui.layout.LargeScreenLayout
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeScreen(
    viewModel: HomeViewModel,
    modifier: Modifier = Modifier,
) {
    val books = viewModel.books.collectAsState()
    val suppliers = viewModel.suppliers.collectAsState()
    val selectedEntity = viewModel.selectedEntity.collectAsState()

    val isBookDialogOpened = viewModel.isBookDialogOpened.collectAsState()
    val bookDialogState = viewModel.bookDialogState.collectAsState()
    val isSupplierDialogOpened = viewModel.isSupplierDialogOpened.collectAsState()
    val supplierDialogState = viewModel.supplierDialogState.collectAsState()

    if (isBookDialogOpened.value && selectedEntity.value == Entity.BOOK) {
        BookDialog(
            bookDialogState = bookDialogState.value,
            onStateChanged = { viewModel.onBookDialogChanged(it) },
            onConfirmRequest = { viewModel.onDialogConfirmed() },
            onDismissRequest = { viewModel.onDialogRejected() }
        )
    }

    LargeScreenLayout(
        navigationRail = { BookstoreNavigationRail(viewModel = viewModel) },
        modifier = modifier,
    ) {
        LazyColumn {
            if (selectedEntity.value.name == Entity.BOOK.name) {
                items(books.value) { book ->
                    val correspondingSupplier =
                        suppliers.value.find { it.supplierId == book.supplierId }
                    BookListItem(
                        book = book,
                        correspondingSupplier = correspondingSupplier,
                        viewModel = viewModel,
                    )
                }
            }

            if (selectedEntity.value.name == Entity.SUPPLIER.name) {
                items(suppliers.value) { supplier ->
                    ListItem(
                        text = { Text("${supplier.supplierId}. ${supplier.supplierName}") },
                        secondaryText = supplier.contactInfo?.let { { Text(it) } }
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun BookListItem(
    book: Book,
    correspondingSupplier: Supplier?,
    viewModel: HomeViewModel,
    modifier: Modifier = Modifier,
) {
    val dropdownMenuState = mutableStateOf(DropdownMenuState())
    ListItem(
        text = { Text("${book.bookId}. ${book.title}") },
        modifier = modifier,
        secondaryText = correspondingSupplier?.let { { Text(it.supplierName) } },
        trailing = {
            Box {
                EntityMoreActionsMenu(
                    dropdownMenuState = dropdownMenuState.value,
                    onEditEntity = {
                        viewModel.onEditBookRequest(book = book)
                        dropdownMenuState.value.status = DropdownMenuState.Status.Closed
                    },
                    onDeleteEntity = {
                        viewModel.onDeleteBookRequest(bookId = book.bookId!!)
                        dropdownMenuState.value.status = DropdownMenuState.Status.Closed
                    }
                )
                IconButton(
                    onClick = {
                        dropdownMenuState.value.status = DropdownMenuState.Status
                            .Open(position = Offset.Zero)
                    }
                ) {
                    Icon(
                        painter = painterResource(Res.drawable.more_icon),
                        contentDescription = null,
                    )
                }
            }
        }
    )
}

@Composable
private fun EntityMoreActionsMenu(
    dropdownMenuState: DropdownMenuState,
    onEditEntity: () -> Unit,
    onDeleteEntity: () -> Unit,
    modifier: Modifier = Modifier,
) {
    DropdownMenu(
        state = dropdownMenuState,
        modifier = modifier,
    ) {
        DropdownMenuItem(
            onClick = onEditEntity,
            modifier = Modifier,
            enabled = true,
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
            interactionSource = MutableInteractionSource(),
        ) {
            Text("Edit")
        }
        DropdownMenuItem(
            onClick = onDeleteEntity,
            modifier = Modifier,
            enabled = true,
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
            interactionSource = MutableInteractionSource(),
        ) {
            Text("Delete", color = MaterialTheme.colors.error)
        }
    }
}