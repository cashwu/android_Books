package com.cashwu.books.utils

/**
 *
 * @author cash.wu
 * @since 2025/03/05
 *
 */
sealed class Screen(val route: String) {
    data object BookListScreen : Screen("books_list_screen")
    data object AddEditBooksScreen : Screen("add_edit_book_screen")
}