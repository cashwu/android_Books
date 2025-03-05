package com.cashwu.books

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.cashwu.books.presentation.ListBookScreen
import com.cashwu.books.presentation.ListBooksViewModel
import com.cashwu.books.ui.theme.BooksTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BooksTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//                    val books = viewModels<ListBooksViewModel>()
                    ListBookScreen(ListBooksViewModel(), innerPadding)
                }
            }
        }
    }
}