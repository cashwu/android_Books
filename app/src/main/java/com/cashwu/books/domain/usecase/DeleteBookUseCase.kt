package com.cashwu.books.domain.usecase

import com.cashwu.books.data.source.BooksDao
import com.cashwu.books.domain.model.Book
import com.cashwu.books.utils.BookException

class DeleteBookUseCase(private val booksDao: BooksDao) {

    @Throws(BookException::class)
    suspend operator fun invoke(book: Book) {
        booksDao.deleteBook(book)
    }

}
