package com.cashwu.books.presentation.list

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cashwu.books.data.source.BooksDao
import com.cashwu.books.presentation.BookVM
import com.cashwu.books.presentation.Fiction
import com.cashwu.books.presentation.components.BookEvent
import com.cashwu.books.presentation.components.SortByAuthor
import com.cashwu.books.presentation.components.SortByFictional
import com.cashwu.books.presentation.components.SortByRead
import com.cashwu.books.presentation.components.SortByTitle
import com.cashwu.books.presentation.components.SortOrder
import com.cashwu.books.utils.getBooks
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

/**
 *
 * @author cash.wu
 * @since 2025/03/05
 *
 */
class ListBooksViewModel(val dao: BooksDao) : ViewModel() {

    private val _books: MutableState<List<BookVM>> = mutableStateOf(emptyList())
    var books: State<List<BookVM>> = _books

    private val _sortOrder: MutableState<SortOrder> = mutableStateOf(SortByAuthor)
    var sortOrder: State<SortOrder> = _sortOrder

    var job: Job? = null

    init {
        loadBooks(sortOrder.value)
    }

    private fun loadBooks(sortOrder: SortOrder) {
//        getBooks(sortOrder).onEach { books ->
//            _books.value = books
//        }.launchIn(viewModelScope)

        job?.cancel()

        job = dao.getBooks().onEach { books ->
            _books.value = books.map {
                BookVM.fromEntity(it)
            }
            sortBook(sortOrder)
        }.launchIn(viewModelScope)
    }

    private fun sortBook(order: SortOrder) {
        _sortOrder.value = order

        _books.value = when (order) {
            SortByAuthor -> books.value.sortedBy { it.author }
            SortByFictional -> books.value.sortedBy { it.bookType == Fiction }
            SortByRead -> books.value.sortedBy { it.read }
            SortByTitle -> books.value.sortedBy { it.title }
        }
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