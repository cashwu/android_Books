package com.cashwu.books.data.source

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.cashwu.books.domain.model.Book
import kotlinx.coroutines.flow.Flow

/**
 *
 * @author cash.wu
 * @since 2025/03/10
 *
 */
@Dao
interface BooksDao {

    @Query("SELECT * FROM books")
    fun getBooks(): Flow<List<Book>>

    @Query("SELECT * FROM books WHERE id = :id")
    suspend fun getBook(id: Int): Book?

    @Upsert
    suspend fun upsertBook(book: Book)

    @Delete
    suspend fun deleteBook(book: Book)
}