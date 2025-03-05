package com.cashwu.books.utils

import com.cashwu.books.presentation.BookVM
import com.cashwu.books.presentation.Fiction
import com.cashwu.books.presentation.NonFiction
import com.cashwu.books.presentation.components.SortByAuthor
import com.cashwu.books.presentation.components.SortByFictional
import com.cashwu.books.presentation.components.SortByRead
import com.cashwu.books.presentation.components.SortByTitle
import com.cashwu.books.presentation.components.SortOrder

fun getBooks(orderBy: SortOrder): List<BookVM> {
    val books: List<BookVM> = listOf(
        BookVM("Catch-22", author = "Joseph Heller", read = true),
        BookVM("To Kill A Mockingbird", author = "Harper Lee", read = true),
        BookVM("A Tale Of Two Cities", author = "Charles Dickens", read = false),
        BookVM(
            "On The Origin Of Species",
            author = "Charles Darwin",
            read = false,
            bookType = NonFiction
        ),
        BookVM(
            "A Brief History Of TIme",
            author = "Stephen Hawkins",
            read = true,
            bookType = NonFiction
        ),
    )
    return when(orderBy) {
        SortByAuthor -> books.sortedBy { it.author }
        SortByFictional -> books.sortedBy { it.bookType == Fiction }
        SortByRead -> books.sortedBy { it.read }
        SortByTitle -> books.sortedBy { it.title }
    }
}


