package com.cashwu.books

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.cashwu.books.presentation.addedit.AddEditBookViewModel
import com.cashwu.books.presentation.list.ListBookScreen
import com.cashwu.books.presentation.list.ListBooksViewModel
import com.cashwu.books.ui.theme.BooksTheme
import com.cashwu.books.utils.AddEditBooksScreen
import com.cashwu.books.utils.BookListScreen
import com.knowledgespike.books.presentation.addedit.AddEditBookScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BooksTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val navController = rememberNavController()

                    NavHost(
                        navController = navController,
                        startDestination = BookListScreen,
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable<BookListScreen> {
                            val books: ListBooksViewModel by viewModels()
                            LaunchedEffect(key1 = true) {
                                books.refreshBooks()
                            }
                            ListBookScreen(navController, books)
                        }

                        composable<AddEditBooksScreen> { navBackStackEntry ->

                            val args : AddEditBooksScreen = navBackStackEntry.toRoute<AddEditBooksScreen>()

                            val books = viewModel<AddEditBookViewModel>(){
                                AddEditBookViewModel(args.bookId)
                            }
                            AddEditBookScreen(navController, books)
                        }
                    }
                }
            }
        }
    }
}