package com.cashwu.books.presentation

import androidx.compose.ui.graphics.Color
import com.cashwu.books.ui.theme.Purple40
import com.cashwu.books.ui.theme.Purple80
import com.cashwu.books.ui.theme.PurpleGrey40
import com.cashwu.books.ui.theme.PurpleGrey80

/**
 *
 * @author cash.wu
 * @since 2025/03/04
 *
 */
data class BookVM(
    val title: String = "",
    val author: String = "",
    val read: Boolean = false,
    val bookType: BookType = Fiction
)

sealed class BookType(val backgroundColor: Color, val foregroundColor: Color)
data object Fiction:BookType(Purple80, PurpleGrey40)
data object NonFiction:BookType(Purple40, PurpleGrey80)

val books: List<BookVM> = listOf(
    BookVM("Catch-22",
        author = "Joeseph Heller",
        read = true),
    BookVM("To Kill A Mockingbird",
     author = "Harper Lee",
      read = true),
    BookVM("A Tale Of Two Cities",
     author = "Charles Dickens",
      read = false),
    BookVM("On The Origin Of Species",
     author = "Charles Darwin",
      read = false,
        bookType = NonFiction),
    BookVM("A Brief History Of Time",
     author = "Stephen Hawkins",
      read = true,
        bookType = NonFiction),
)
