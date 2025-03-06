package com.cashwu.books.presentation.addedit

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.viewModelScope
import com.cashwu.books.presentation.BookVM
import com.cashwu.books.presentation.components.SortByAuthor
import com.cashwu.books.utils.BookException
import com.cashwu.books.utils.addOrUpdateBook
import com.cashwu.books.utils.getBooks
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class AddEditBookViewModel(bookId: Int = -1) : ViewModel() {

    private val _book = mutableStateOf(BookVM())
    val book: State<BookVM> = _book

    private val _eventFlow = MutableSharedFlow<AddEditBookUiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private fun findBook(bookId: Int) {
        _book.value = getBooks(bookId) ?: BookVM()
    }

    init {
        findBook(bookId)
    }

    fun onEvent(event: AddEditBookEvent) {
        when (event) {
            is AddEditBookEvent.EnteredAuthor -> {
                _book.value = _book.value.copy(author = event.author)
            }

            is AddEditBookEvent.EnteredTitle -> {
                _book.value = _book.value.copy(title = event.title)
            }

            AddEditBookEvent.BookRead -> _book.value = _book.value.copy(read = !_book.value.read)
            is AddEditBookEvent.TypeChanged -> {
                _book.value = _book.value.copy(bookType = event.bookType)
            }

            AddEditBookEvent.SaveBook -> {
                viewModelScope.launch {

                    try {
                        addOrUpdateBook(book.value)
                        _eventFlow.emit(AddEditBookUiEvent.SaveBook)
                    } catch (e: BookException) {
                        _eventFlow.emit(AddEditBookUiEvent.ShowMessage(e.message!!))

                    }
                }
            }

        }
    }
}

sealed interface AddEditBookUiEvent {
    data class ShowMessage(val message: String) : AddEditBookUiEvent
    data object SaveBook : AddEditBookUiEvent
}

