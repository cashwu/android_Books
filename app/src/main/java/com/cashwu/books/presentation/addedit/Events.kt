package com.cashwu.books.presentation.addedit

import com.cashwu.books.presentation.BookType

sealed interface AddEditBookEvent {
    data class EnteredAuthor(val author: String) : AddEditBookEvent
    data class EnteredTitle(val title: String): AddEditBookEvent
    data object BookRead: AddEditBookEvent
    data object SaveBook: AddEditBookEvent
    data class TypeChanged(val bookType: BookType): AddEditBookEvent
}