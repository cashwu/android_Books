package com.cashwu.books.presentation.addedit

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cashwu.books.domain.usecase.BooksUseCases
import com.cashwu.books.presentation.BookVM
import com.cashwu.books.presentation.toEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditBookViewModel @Inject constructor
    (private val booksUseCases: BooksUseCases,
     savedStateHandle: SavedStateHandle) : ViewModel() {

    private val _book = mutableStateOf(BookVM())
    val book: State<BookVM> = _book

    private val _eventFlow = MutableSharedFlow<AddEditBookUiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private fun findBook(bookId: Int) {

        viewModelScope.launch {
           val bookEntity = booksUseCases.getBook(bookId)
            _book.value = bookEntity?.let { BookVM.fromEntity(it) } ?: BookVM()
        }
    }

    init {
        val bookId = savedStateHandle.get<Int>("bookId") ?: -1
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
                    if (book.value.title.isEmpty() || book.value.author.isEmpty()) {
                        _eventFlow.emit(AddEditBookUiEvent.ShowMessage("unable to save book"))
                    } else {
                        val entity = book.value.toEntity()
                        booksUseCases.upsertBook(entity)
                        _eventFlow.emit(AddEditBookUiEvent.SaveBook)
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

