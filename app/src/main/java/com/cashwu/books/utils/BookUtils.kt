package com.cashwu.books.utils

import com.cashwu.books.presentation.list.BookVM
import com.cashwu.books.presentation.list.Fiction
import com.cashwu.books.presentation.list.NonFiction
import com.cashwu.books.presentation.components.SortByAuthor
import com.cashwu.books.presentation.components.SortByFictional
import com.cashwu.books.presentation.components.SortByRead
import com.cashwu.books.presentation.components.SortByTitle
import com.cashwu.books.presentation.components.SortOrder

private val booksList: MutableList<BookVM> = mutableListOf(
    BookVM(id = 1, title = "Catch-22", author = "Joeseph Heller", read = true),
    BookVM(id = 2, title = "To Kill A Mockingbird", author = "Harper Lee", read = true),
    BookVM(id = 3, title = "A Tale Of Two Cities", author = "Charles Dickens", read = false),
    BookVM(
        id = 4,
        title = "On The Origin Of Species",
        author = "Charles Darwin",
        read = false,
        bookType = NonFiction
    ),
    BookVM(
        id = 5,
        title = "A Brief History Of TIme",
        author = "Stephen Hawkins",
        read = true,
        bookType = NonFiction
    ),
)

fun getBooks(orderBy: SortOrder): List<BookVM> {
    return when(orderBy) {
        SortByAuthor -> booksList.sortedBy { it.author }
        SortByFictional -> booksList.sortedBy { it.bookType == Fiction }
        SortByRead -> booksList.sortedBy { it.read }
        SortByTitle -> booksList.sortedBy { it.title }
    }
}

fun addOrUpdateBook(book: BookVM) {
    val existingBook = booksList.find { it.id == book.id }

    existingBook?.let {
        booksList.remove(it)
    }
    booksList.add(book)

}

fun deleteBook(book: BookVM) {
    booksList.remove(book)
}