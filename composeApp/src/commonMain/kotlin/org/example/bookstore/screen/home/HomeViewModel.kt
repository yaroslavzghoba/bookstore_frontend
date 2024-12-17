package org.example.bookstore.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import org.example.bookstore.model.Book
import org.example.bookstore.model.Entity
import org.example.bookstore.model.Repository
import org.example.bookstore.model.Supplier

class HomeViewModel(private val repository: Repository) : ViewModel() {

    private val _books: MutableStateFlow<List<Book>> = MutableStateFlow(emptyList())
    val books: StateFlow<List<Book>> = _books.asStateFlow()
    private val _selectedBook: MutableStateFlow<Book?> = MutableStateFlow(null)
    val selectedBook: StateFlow<Book?> = _selectedBook.asStateFlow()
    private val _isBookDialogOpened: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isBookDialogOpened: StateFlow<Boolean> = _isBookDialogOpened.asStateFlow()
    private val _bookDialogState: MutableStateFlow<BookDialogState> =
        MutableStateFlow(BookDialogState())
    val bookDialogState: StateFlow<BookDialogState> = _bookDialogState.asStateFlow()

    private val _suppliers: MutableStateFlow<List<Supplier>> = MutableStateFlow(emptyList())
    val suppliers: StateFlow<List<Supplier>> = _suppliers.asStateFlow()
    private val _selectedSupplier: MutableStateFlow<Supplier?> = MutableStateFlow(null)
    val selectedSupplier: StateFlow<Supplier?> = _selectedSupplier.asStateFlow()
    private val _isSupplierDialogOpened: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isSupplierDialogOpened: StateFlow<Boolean> = _isSupplierDialogOpened.asStateFlow()
    private val _supplierDialogState: MutableStateFlow<SupplierDialogState> =
        MutableStateFlow(SupplierDialogState())
    val supplierDialogState: StateFlow<SupplierDialogState> = _supplierDialogState.asStateFlow()

    private val _selectedEntity: MutableStateFlow<Entity> = MutableStateFlow(Entity.BOOK)
    val selectedEntity: StateFlow<Entity> = _selectedEntity.asStateFlow()


    init {
        viewModelScope.launch {
            updateBooks()
            updateSuppliers()
        }
    }

    fun onEntitySelected(entity: Entity) {
        _selectedEntity.value = entity
    }

    fun onAddNewEntity() {
        when (_selectedEntity.value) {
            Entity.BOOK -> {
                _selectedBook.value = null
                _bookDialogState.value = BookDialogState()
                _isBookDialogOpened.value = true
            }
            Entity.SUPPLIER -> {
                _selectedSupplier.value = null
                _supplierDialogState.value = SupplierDialogState()
                _isSupplierDialogOpened.value = true
            }
        }
    }

    fun onBookDialogChanged(bookDialogState: BookDialogState) {
        _bookDialogState.value = bookDialogState
    }

    fun onEditBookRequest(book: Book) {
        _selectedBook.value = book
        _bookDialogState.value = book.toBookDialogState()
        _isBookDialogOpened.value = true
        updateBooks()
    }

    fun onDeleteBookRequest(bookId: Long) {
        viewModelScope.launch {
            repository.deleteBookById(id = bookId)
            updateBooks()
        }
    }

    fun onSupplierDialogChanged(supplierDialogState: SupplierDialogState) {
        _supplierDialogState.value = supplierDialogState
    }

    fun onEditSupplierRequest(supplier: Supplier) {
        _selectedSupplier.value = supplier
        _supplierDialogState.value = supplier.toSupplierDialogState()
        _isSupplierDialogOpened.value = true
        updateSuppliers()
    }

    fun onDeleteSupplierRequest(supplierId: Long) {
        viewModelScope.launch {
            repository.deleteSupplierById(id = supplierId)
            updateSuppliers()
        }
    }

    fun onDialogRejected() {
        _selectedBook.value = null
        _bookDialogState.value = BookDialogState()
        _isBookDialogOpened.value = false
        _selectedSupplier.value = null
        _supplierDialogState.value = SupplierDialogState()
        _isSupplierDialogOpened.value = false
    }

    fun onDialogConfirmed() {
        viewModelScope.launch {
            when (_selectedEntity.value) {
                Entity.BOOK -> {
                    val bookId = _selectedBook.value?.bookId
                    val book = _bookDialogState.value.toBook(id = bookId)
                    if (bookId == null) {
                        repository.insertBook(book = book)
                    } else {
                        repository.updateBook(book = book)
                    }
                    _isBookDialogOpened.value = false
                    _selectedBook.value = null
                    updateBooks()

                }
                Entity.SUPPLIER -> {
                    val supplierId = _selectedSupplier.value?.supplierId
                    val supplier = _supplierDialogState.value.toSupplier(id = supplierId)
                    if (supplierId == null) {
                        repository.insertSupplier(supplier = supplier)
                    } else {
                        repository.updateSupplier(supplier = supplier)
                    }
                    _isSupplierDialogOpened.value = false
                    _selectedSupplier.value = null
                    _supplierDialogState.value = SupplierDialogState()
                    updateSuppliers()
                }
            }
        }
    }

    private fun updateBooks() {
        viewModelScope.launch {
            _books.value = repository.getAllBooks()
        }
    }

    private fun updateSuppliers() {
        viewModelScope.launch {
            _suppliers.value = repository.getAllSuppliers()
        }
    }
}