package com.cashwu.books.domain.usecase

import com.cashwu.books.data.source.BooksDao
import com.cashwu.books.domain.model.Book

class GetBookUseCase(private val booksDao: BooksDao) {
    suspend operator fun invoke(bookId: Int) : Book? {
        return booksDao.getBook(bookId)
    }
}
