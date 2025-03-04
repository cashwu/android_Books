package com.cashwu.books.presentation

/**
 *
 * @author cash.wu
 * @since 2025/03/04
 *
 */
data class BookVM(
    val title: String = "",
    val author: String = "",
    val read: Boolean = false
)

val books: List<BookVM> = listOf(
    BookVM("Catch-22", author = "Joeseph Heller", read = true),
    BookVM("To Kill A Mockingbird", author = "Harper Lee", read = true),
    BookVM("A Tale Of Two Cities", author = "Charles Dickens", read = false),
    BookVM("On The Origin Of Species", author = "Charles Darwin", read = false),
    BookVM("A Brief History Of Time", author = "Stephen Hawkins", read = true),
)
