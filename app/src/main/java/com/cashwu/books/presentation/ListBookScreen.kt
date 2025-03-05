package com.cashwu.books.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cashwu.books.R
import com.cashwu.books.presentation.components.BookCard
import com.cashwu.books.presentation.components.BookEvent
import com.cashwu.books.presentation.components.SortOptions

/**
 *
 * @author cash.wu
 * @since 2025/03/04
 *
 */
@Composable
fun ListBookScreen(booksViewModel: ListBooksViewModel, innerPadding: PaddingValues) {

    Column(
        modifier = Modifier
            .padding(innerPadding)
            .padding(horizontal = 8.dp)
            .fillMaxSize()
    ) {

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            textAlign = TextAlign.Center,
            text = stringResource(id = R.string.main_heading),
            style = TextStyle(fontSize = 32.sp)
        )


        SortOptions(bookOrder = booksViewModel.sortOrder.value, onSortOrderChange = { order ->
            booksViewModel.onEvent(BookEvent.Order(order))
        })

        Spacer(modifier = Modifier.height(8.dp))

        LazyColumn {
            items(booksViewModel.books.value) { book ->
                BookCard(book, onDeleteClick = {
                    booksViewModel.onEvent(BookEvent.Delete(book))
                })
                Spacer(modifier = Modifier.height(8.dp))
            }
        }

    }
}