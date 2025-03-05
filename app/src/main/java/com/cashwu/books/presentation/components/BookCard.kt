package com.cashwu.books.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cashwu.books.R
import com.cashwu.books.presentation.BookVM

/**
 *
 * @author cash.wu
 * @since 2025/03/04
 *
 */
@Composable
fun BookCard(book: BookVM, onDeleteClick: (BookVM) -> Unit, modifier: Modifier) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = book.bookType.backgroundColor,
                shape = RoundedCornerShape(10.dp)
            )
            .padding(16.dp)
            .then(modifier)
    ) {
        Column {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        book.title,
                        style = TextStyle(fontSize = 33.sp, color = book.bookType.foregroundColor),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
                if (book.read) {
                    Icon(
                        imageVector = Icons.Filled.Check,
                        contentDescription = stringResource(id = R.string.delete)
                    )
                }
            }
            Text(
                book.author,
                style = TextStyle(fontSize = 32.sp, color = book.bookType.foregroundColor),
                maxLines = 10,
                overflow = TextOverflow.Ellipsis
            )
        }
        IconButton(
            modifier = Modifier.align(Alignment.BottomEnd),
            onClick = { onDeleteClick(book) }
        ) {
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = stringResource(id = R.string.delete),
                tint = Color.White
            )
        }
    }
}