package com.cashwu.books

import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.test.printToLog
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.cashwu.books.di.AppModule
import com.cashwu.books.presentation.addedit.AddEditBookScreen
import com.cashwu.books.presentation.list.ListBookScreen
import com.cashwu.books.ui.theme.BooksTheme
import com.cashwu.books.utils.AddEditBooksScreen
import com.cashwu.books.utils.BookListScreen
import com.cashwu.books.utils.test.TestTags
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 *
 * @author cash.wu
 * @since 2025/03/12
 *
 */
@HiltAndroidTest
@UninstallModules(AppModule::class)
class ListScreenIntegrationTest {

    @get: Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get: Rule(order = 1)
    val composeRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun setUp() {
        hiltRule.inject()

        composeRule.activity.setContent {
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

    @Test
    fun `create new note`() {
        composeRule.onNodeWithContentDescription("Add")
            .performClick()

        composeRule.onNodeWithTag(TestTags.ADD_EDIT_AUTHOR_FIELD)
            .performTextInput("test author")

        composeRule.onNodeWithTag(TestTags.ADD_EDIT_TITLE_FIELD)
            .performTextInput("test title")

        composeRule.onNodeWithContentDescription("Save book")
            .performClick()

        composeRule.onNodeWithText("test title")
            .assertIsDisplayed()
        composeRule.onNodeWithText("test author")
            .assertIsDisplayed()
    }

    @Test
    fun `create a new note and display for edit`() {
        composeRule.onNodeWithContentDescription("Add")
            .performClick()

        // add edit screen
        composeRule.onNodeWithTag(TestTags.ADD_EDIT_AUTHOR_FIELD)
            .performTextInput("test author")

        composeRule.onNodeWithTag(TestTags.ADD_EDIT_TITLE_FIELD)
            .performTextInput("test title")

        composeRule.onNodeWithContentDescription("Save book")
            .performClick()

        // list screen
        composeRule.onNodeWithTag(TestTags.BOOK_CARD)
            .performClick()

        // add edit screen
        composeRule.onNodeWithText("test title")
            .assertIsDisplayed()
        composeRule.onNodeWithText("test author")
            .assertIsDisplayed()
    }

    @Test
    fun `create and then edit a note`() {
        composeRule.onNodeWithContentDescription("Add")
            .performClick()

        // add edit screen
        composeRule.onNodeWithTag(TestTags.ADD_EDIT_AUTHOR_FIELD)
            .performTextInput("test author")

        composeRule.onNodeWithTag(TestTags.ADD_EDIT_TITLE_FIELD)
            .performTextInput("test title")

        composeRule.onNodeWithContentDescription("Save book")
            .performClick()

        // list screen
        composeRule.onNodeWithTag(TestTags.BOOK_CARD)
            .performClick()

        // add edit screen
//        composeRule.onNodeWithTag(TestTags.ADD_EDIT_TITLE_FIELD)
//            .assertTextContains("test title")
//
//        composeRule.onNodeWithTag(TestTags.ADD_EDIT_AUTHOR_FIELD)
//            .assertTextEquals("Author", "test author")

        // modify
        composeRule.onNodeWithTag(TestTags.ADD_EDIT_TITLE_FIELD)
            .performTextInput("2")

        composeRule.onNodeWithTag(TestTags.ADD_EDIT_TITLE_FIELD)
            .printToLog("TEST")

        composeRule.onNodeWithContentDescription("Save book")
            .performClick()

        // list screen
        composeRule.onNodeWithText("2test title")
            .assertIsDisplayed()

//        composeRule.onRoot()
//            .printToLog("TEST")

    }
}