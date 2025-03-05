package com.cashwu.books.presentation.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.cashwu.books.presentation.BookVM

@Composable
fun SortOptions(
    bookOrder: SortOrder = SortByAuthor,
    onSortOrderChange: (SortOrder) -> Unit
) {
    Row(modifier = Modifier.fillMaxWidth()) {
        BooksRadioButton(text = "Author",
            selected = bookOrder is SortByAuthor,
            onSelect = { onSortOrderChange(SortByAuthor) })

        Spacer(modifier = Modifier.width(8.dp))

        BooksRadioButton(text = "Title",
            selected = bookOrder is SortByTitle,
            onSelect = { onSortOrderChange(SortByTitle) })

    }
    Spacer(modifier = Modifier.height(8.dp))
    Row() {

        BooksRadioButton(text = "Fictional",
            selected = bookOrder is SortByFictional,
            onSelect = { onSortOrderChange(SortByFictional) })

        Spacer(modifier = Modifier.width(8.dp))

        BooksRadioButton(text = "Read",
            selected = bookOrder is SortByRead,
            onSelect = { onSortOrderChange(SortByRead) })

    }
}

sealed class SortOrder()
data object SortByAuthor : SortOrder()
data object SortByTitle : SortOrder()
data object SortByRead : SortOrder()
data object SortByFictional : SortOrder()

data class NotesState(
    val books: List<BookVM> = emptyList(),
    val bookOrder: SortOrder = SortByAuthor,
)

sealed class BookEvent {
    data class Order(val order: SortOrder)
}