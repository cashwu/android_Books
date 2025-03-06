package com.cashwu.books.utils

import kotlinx.serialization.Serializable

/**
 *
 * @author cash.wu
 * @since 2025/03/05
 *
 */
@Serializable
object BookListScreen

@Serializable
data class AddEditBooksScreen(val bookId: Int)