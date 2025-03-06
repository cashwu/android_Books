package com.cashwu.books.presentation.list

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cashwu.books.presentation.BookVM
import com.cashwu.books.presentation.components.BookEvent
import com.cashwu.books.presentation.components.SortByAuthor
import com.cashwu.books.presentation.components.SortOrder
import com.cashwu.books.utils.getBooks
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

/**
 *
 * @author cash.wu
 * @since 2025/03/05
 *
 */
class ListBooksViewModel : ViewModel() {

    private val _books: MutableState<List<BookVM>> = mutableStateOf(emptyList())
    var books: State<List<BookVM>> = _books

    private val _sortOrder: MutableState<SortOrder> = mutableStateOf(SortByAuthor)
    var sortOrder: State<SortOrder> = _sortOrder

    init {
        loadBooks(sortOrder.value)
    }

    private fun loadBooks(sortOrder: SortOrder) {
        getBooks(sortOrder).onEach { books ->
            _books.value = books
        }.launchIn(viewModelScope)
    }

    fun onEvent(event: BookEvent) {
        when (event) {
            is BookEvent.Delete -> {
                deleteBook(event.book)
            }

            is BookEvent.Order -> {
                _sortOrder.value = event.order
                loadBooks(event.order)
            }
        }
    }

    fun refreshBooks() {
        loadBooks(sortOrder.value)
    }

    private fun deleteBook(book: BookVM) {
        _books.value = _books.value.filter { it != book }
    }
}