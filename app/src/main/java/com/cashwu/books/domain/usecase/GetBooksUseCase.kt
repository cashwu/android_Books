package com.cashwu.books.domain.usecase

import com.cashwu.books.data.source.BooksDao
import com.cashwu.books.domain.model.Book
import com.cashwu.books.presentation.components.SortByAuthor
import com.cashwu.books.presentation.components.SortByFictional
import com.cashwu.books.presentation.components.SortByRead
import com.cashwu.books.presentation.components.SortByTitle
import com.cashwu.books.presentation.components.SortOrder
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetBooksUseCase(private val booksDao: BooksDao) {
    operator fun invoke(orderBy: SortOrder) : Flow<List<Book>> {

        return booksDao.getBooks()
            .map { books ->
                when(orderBy) {
                    SortByAuthor -> books.sortedBy { it.author }
                    SortByFictional -> books.sortedBy { it.bookType }
                    SortByRead -> books.sortedBy { it.read }
                    SortByTitle -> books.sortedBy { it.title }
                }
            }

    }

}
