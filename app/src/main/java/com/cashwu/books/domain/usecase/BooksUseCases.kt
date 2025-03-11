package com.cashwu.books.domain.usecase

/**
 *
 * @author cash.wu
 * @since 2025/03/11
 *
 */
data class BooksUseCases(
    val getBooks : GetBooksUseCase,
    val getBook : GetBookUseCase,
    val upsertBook : UpsertBookUseCase,
    val deleteBook : DeleteBookUseCase,
)
