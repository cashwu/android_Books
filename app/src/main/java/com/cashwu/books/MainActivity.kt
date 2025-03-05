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
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.cashwu.books.presentation.list.ListBookScreen
import com.cashwu.books.presentation.list.ListBooksViewModel
import com.cashwu.books.presentation.addedit.AddEditBookViewModel
import com.cashwu.books.ui.theme.BooksTheme
import com.cashwu.books.utils.Screen
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
                        startDestination = Screen.BookListScreen.route,
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable(route = Screen.BookListScreen.route) {
                            val books: ListBooksViewModel by viewModels()
                            LaunchedEffect(key1 = true) {
                                books.refreshBooks()
                            }
                            ListBookScreen(navController, books)
                        }

                        composable(route = Screen.AddEditBooksScreen.route + "?bookId={bookId}",
                            arguments = listOf(
                                navArgument(name = "bookId") {
                                    type = NavType.IntType
                                    defaultValue = -1
                                }
                            )
                        ) { navBackStackEntry ->

                            var bookId = navBackStackEntry.arguments?.getInt("bookId") ?: -1

                            val books = viewModel<AddEditBookViewModel>(){
                                AddEditBookViewModel(bookId)
                            }
                            AddEditBookScreen(navController, books)
                        }
                    }
                }
            }
        }
    }
}