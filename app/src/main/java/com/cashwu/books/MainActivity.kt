package com.cashwu.books

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.cashwu.books.data.source.BooksDatabase
import com.cashwu.books.presentation.Greeting
import com.cashwu.books.presentation.list.ListBookScreen
import com.cashwu.books.ui.theme.BooksTheme
import com.cashwu.books.utils.AddEditBooksScreen
import com.cashwu.books.utils.BookListScreen
import com.cashwu.books.presentation.addedit.AddEditBookScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val db by lazy {
        Room.databaseBuilder(
            applicationContext,
            BooksDatabase::class.java,
            BooksDatabase.DATABASE_NAME
        ).build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

//            BooksTheme {
//                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//                    Greeting(
//                        name = "Android",
//                        modifier = Modifier
//                            .padding(innerPadding)
//                            .fillMaxSize()
//                    )
//                }
//            }

            BooksTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val navController = rememberNavController()

                    NavHost(
                        navController = navController,
                        startDestination = BookListScreen,
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable<BookListScreen> {
                            ListBookScreen(navController)
                        }

                        composable<AddEditBooksScreen> { navBackStackEntry ->
                            AddEditBookScreen(navController)
                        }
                    }
                }
            }
        }
    }
}