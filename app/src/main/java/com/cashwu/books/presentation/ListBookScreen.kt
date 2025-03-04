package com.cashwu.books.presentation

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.cashwu.books.presentation.components.BookCard

/**
 * 
 * @author cash.wu 
 * @since 2025/03/04 
 * 
 */
@Composable
fun ListBookScreen(innerPadding: PaddingValues) {
    LazyColumn (modifier = Modifier
        .padding(innerPadding)
        .border(1.dp, color = Color.Blue)
        .fillMaxSize()
    ) {
        books.forEach { book ->
            item {
                BookCard(book) {
                    println("delete book")
                    books.remove(book)
                }
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}