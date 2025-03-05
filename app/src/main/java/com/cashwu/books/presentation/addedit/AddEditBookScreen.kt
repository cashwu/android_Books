package com.knowledgespike.books.presentation.addedit

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Checkbox
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.cashwu.books.R
import com.cashwu.books.presentation.addedit.AddEditBookEvent
import com.cashwu.books.presentation.addedit.AddEditBookViewModel
import com.cashwu.books.presentation.components.HorizontalTextRadioButton
import com.cashwu.books.presentation.Fiction
import com.cashwu.books.presentation.NonFiction
import com.cashwu.books.utils.Screen

@Composable
fun AddEditBookScreen(
    navController: NavHostController,
    viewModel: AddEditBookViewModel
) {

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    viewModel.onEvent(AddEditBookEvent.SaveBook)
                    navController.navigate(Screen.BookListScreen.route)
                },
            )
            {
                Icon(imageVector = Icons.Default.Edit, contentDescription = "Save book")
            }
        }
    ) { contentPadding ->
        val book = viewModel.book.value
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding)
                .background(color = book.bookType.backgroundColor)
        ) {
            Text(
                style = MaterialTheme.typography.headlineLarge,
                text = stringResource(id = R.string.add_edit_book),
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(16.dp)
            )
            OutlinedTextField(
                value = book.author,
                label = { Text("Author") },
                onValueChange = {
                    viewModel.onEvent(AddEditBookEvent.EnteredAuthor(it))
                },
                singleLine = true,
                textStyle = MaterialTheme.typography.headlineMedium.copy(color = book.bookType.foregroundColor),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = book.title,
                label = { Text("Title") },
                onValueChange = {
                    viewModel.onEvent(AddEditBookEvent.EnteredTitle(it))
                },
                singleLine = true,
                textStyle = MaterialTheme.typography.headlineMedium.copy(color = book.bookType.foregroundColor),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "Read",
                    modifier = Modifier.align(Alignment.CenterVertically),
                    style = MaterialTheme.typography.headlineMedium.copy(color = book.bookType.foregroundColor),
                )
                Checkbox(
                    checked = book.read,
                    onCheckedChange = {
                        viewModel.onEvent(AddEditBookEvent.BookRead)
                    }
                )
            }
            Row(modifier = Modifier.fillMaxWidth()) {

                Row(modifier = Modifier.fillMaxWidth()) {

                    HorizontalTextRadioButton(
                        selected = book.bookType::class == Fiction::class,
                        text = "Fiction",
                        color = book.bookType.foregroundColor,
                        onOptionSelected = {
                            viewModel.onEvent(AddEditBookEvent.TypeChanged(Fiction))
                        })
                    HorizontalTextRadioButton(
                        selected = book.bookType::class == NonFiction::class,
                        text = "Non-fiction",
                        color = book.bookType.foregroundColor,
                        onOptionSelected = {
                            viewModel.onEvent(AddEditBookEvent.TypeChanged(NonFiction))
                        })
                }

            }
        }
    }
}


