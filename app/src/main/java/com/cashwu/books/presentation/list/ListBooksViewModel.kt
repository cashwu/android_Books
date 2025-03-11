package com.cashwu.books.presentation.list

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cashwu.books.domain.usecase.BooksUseCases
import com.cashwu.books.presentation.BookVM
import com.cashwu.books.presentation.components.BookEvent
import com.cashwu.books.presentation.components.SortByAuthor
import com.cashwu.books.presentation.components.SortOrder
import com.cashwu.books.presentation.toEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 *
 * @author cash.wu
 * @since 2025/03/05
 *
 */
@HiltViewModel
class ListBooksViewModel @Inject constructor
    (private val booksUseCases: BooksUseCases) : ViewModel() {

    private val _books: MutableState<List<BookVM>> = mutableStateOf(emptyList())
    var books: State<List<BookVM>> = _books

    private val _sortOrder: MutableState<SortOrder> = mutableStateOf(SortByAuthor)
    var sortOrder: State<SortOrder> = _sortOrder

    var job: Job? = null

    init {
        loadBooks(sortOrder.value)
    }

    private fun loadBooks(sortOrder: SortOrder) {
        job?.cancel()

        job = booksUseCases.getBooks(sortOrder).onEach { books ->
            _books.value = books.map {
                BookVM.fromEntity(it)
            }
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

    private fun deleteBook(book: BookVM) {
        viewModelScope.launch {
            booksUseCases.deleteBook(book.toEntity())
        }
    }
}