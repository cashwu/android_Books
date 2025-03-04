package com.cashwu.books.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.core.provider.FontsContractCompat.Columns
import com.cashwu.books.presentation.BookVM

/**
 *
 * @author cash.wu
 * @since 2025/03/04
 *
 */
@Composable
fun BookCard(book: BookVM) {

    Column {
        Text(book.title)
        Text(book.author)
    }
}