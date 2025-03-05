package com.cashwu.books.presentation.addedit

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.cashwu.books.presentation.list.BookVM
import com.cashwu.books.presentation.addedit.AddEditBookEvent
import com.cashwu.books.utils.addOrUpdateBook

class AddEditBookViewModel() : ViewModel() {

    private val _book = mutableStateOf(BookVM())
    val book: State<BookVM> = _book

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
                addOrUpdateBook(book.value)
            }

        }
    }
}

