package com.cashwu.books.presentation.list

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.cashwu.books.R
import com.cashwu.books.presentation.components.BookCard
import com.cashwu.books.presentation.components.BookEvent
import com.cashwu.books.presentation.components.SortOptions
import com.cashwu.books.utils.AddEditBooksScreen
import com.cashwu.books.utils.test.TestTags
import kotlinx.coroutines.launch

/**
 *
 * @author cash.wu
 * @since 2025/03/04
 *
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListBookScreen(
    navController: NavController,
    booksViewModel: ListBooksViewModel = hiltViewModel()
) {

    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate(AddEditBooksScreen(-1))
                },
                modifier = Modifier.background(Color.White)
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add")
            }
        }
    ) { contentPadding ->
        Column(
            modifier = Modifier
                .padding(contentPadding)
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
                    BookCard(
                        book,
                        onDeleteClick = {
                            booksViewModel.onEvent(BookEvent.Delete(book))
                            scope.launch {
                                snackbarHostState.showSnackbar("Deleted book successfully")
                            }
                        },
                        modifier = Modifier.clickable {
                            navController.navigate(AddEditBooksScreen(book.id))
                        }.testTag(TestTags.BOOK_CARD)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
    }
}