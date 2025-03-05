package com.cashwu.books.presentation.list

import androidx.compose.ui.graphics.Color
import com.cashwu.books.presentation.components.BookEvent
import com.cashwu.books.presentation.components.SortByAuthor
import com.cashwu.books.presentation.components.SortByFictional
import com.cashwu.books.presentation.components.SortByRead
import com.cashwu.books.presentation.components.SortByTitle
import com.cashwu.books.ui.theme.Purple40
import com.cashwu.books.ui.theme.Purple80
import com.cashwu.books.ui.theme.PurpleGrey40
import com.cashwu.books.ui.theme.PurpleGrey80
import kotlin.random.Random

/**
 *
 * @author cash.wu
 * @since 2025/03/04
 *
 */
data class BookVM(
    val id: Int = Random.nextInt(),
    val title: String = "",
    val author: String = "",
    val read: Boolean = false,
    val bookType: BookType = Fiction
)

sealed class BookType(val backgroundColor: Color, val foregroundColor: Color)
data object Fiction: BookType(Purple80, PurpleGrey40)
data object NonFiction: BookType(Purple40, PurpleGrey80)