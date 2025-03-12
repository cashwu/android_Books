package com.cashwu.books.presentation

import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import com.cashwu.books.MainActivity
import com.cashwu.books.ui.theme.BooksTheme
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 *
 * @author cash.wu
 * @since 2025/03/12
 *
 */
class GreetingTest {


    @get: Rule()
    val composeRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun setUp() {
        composeRule.activity.setContent {

            BooksTheme {
                Scaffold(modifier = Modifier.Companion.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.Companion
                            .padding(innerPadding)
                            .fillMaxSize()
                    )
                }
            }
        }
    }

    @Test
    fun `greeting should be hidden`() {
        composeRule.onNodeWithTag("txt")
            .assertDoesNotExist()
    }

    @Test
    fun `greeting should appear when button is clicked`() {

        composeRule.onNodeWithTag("btn")
            .performClick()

        composeRule.onNodeWithTag("txt")
            .assertExists()
    }

    @Test
    fun `greeting should disappear when button is clicked`() {

        composeRule.onNodeWithTag("btn")
            .performClick()

        composeRule.onNodeWithTag("btn")
            .performClick()

        composeRule.onNodeWithTag("txt")
            .assertDoesNotExist()
    }
}