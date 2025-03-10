package com.cashwu.books.data.source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.cashwu.books.domain.model.Book

/**
 *
 * @author cash.wu
 * @since 2025/03/10
 *
 */
@Database(entities = [Book::class], version = 1)
abstract class BooksDatabase : RoomDatabase() {

    abstract val dao : BooksDao

    companion object {
        const val DATABASE_NAME = "books.db"
    }
}