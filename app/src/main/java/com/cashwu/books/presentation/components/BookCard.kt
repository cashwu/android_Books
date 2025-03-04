package com.cashwu.books.presentation.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
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

    Column(modifier = Modifier
        .border(width = 1.dp, color = Color.Red)
        .fillMaxSize()
    ) {
        Text(book.title)
        Text(book.author)
    }
}