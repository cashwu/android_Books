package com.cashwu.books.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

/**
 * 
 * @author cash.wu 
 * @since 2025/03/04 
 * 
 */
@Composable
fun ListBookScreen(innerPadding: PaddingValues) {
    Row(modifier = Modifier.padding(innerPadding)) {
        Text("hello")
        Text("goodbye")
    }
}