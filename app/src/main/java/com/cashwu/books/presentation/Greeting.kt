package com.cashwu.books.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.room.util.TableInfo

/**
 *
 * @author cash.wu
 * @since 2025/03/12
 *
 */
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {

    var greeting by remember { mutableStateOf(false) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
    ) {
        Button(modifier = Modifier.testTag("btn"),
            onClick = {
                greeting = !greeting
            }) {
            Text("Show greeting")
        }

        if (greeting) {
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                text = "Hello $name!",
                modifier = Modifier.testTag("txt")
            )
        }
    }


}