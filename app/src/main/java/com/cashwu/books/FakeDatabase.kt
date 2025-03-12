package com.cashwu.books

import com.cashwu.books.data.source.BooksDao
import com.cashwu.books.domain.model.Book
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 *
 * @author cash.wu
 * @since 2025/03/12
 *
 */
class FakeDatabase : BooksDao {

    val books = mutableListOf<Book>()

    override fun getBooks(): Flow<List<Book>> = flow {
        emit(books)
    }

    override suspend fun getBook(id: Int): Book? {
        return books.find { it.id == id }
    }

    override suspend fun upsertBook(book: Book) {
        if (books.contains(book)) {
            books.remove(book)
        }

        books.add(book)
    }

    override suspend fun deleteBook(book: Book) {
        books.remove(book)
    }
}