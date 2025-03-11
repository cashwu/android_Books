package com.cashwu.books.domain.usecase

import com.cashwu.books.data.source.BooksDao
import com.cashwu.books.domain.model.Book
import com.cashwu.books.utils.BookException
import kotlin.jvm.Throws

class UpsertBookUseCase(private val booksDao: BooksDao) {

    @Throws(BookException::class)
    suspend operator fun invoke(book: Book) {

        if (book.title.isEmpty() || book.author.isEmpty()) {
            throw BookException("book data is invalid")
        }

        booksDao.upsertBook(book)
    }

}
