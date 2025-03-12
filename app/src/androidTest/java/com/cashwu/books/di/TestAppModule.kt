package com.cashwu.books.di

import android.app.Application
import androidx.room.Room
import com.cashwu.books.data.source.BooksDatabase
import com.cashwu.books.domain.usecase.BooksUseCases
import com.cashwu.books.domain.usecase.DeleteBookUseCase
import com.cashwu.books.domain.usecase.GetBookUseCase
import com.cashwu.books.domain.usecase.GetBooksUseCase
import com.cashwu.books.domain.usecase.UpsertBookUseCase
import com.cashwu.books.utils.getBooks
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.testing.UninstallModules
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 *
 * @author cash.wu
 * @since 2025/03/11
 *
 */

@Module
@InstallIn(SingletonComponent::class)
object TestAppModule {

    @Provides
    @Singleton
    fun provideBooksDatabase(context: Application): BooksDatabase {
        return Room.inMemoryDatabaseBuilder(
            context,
            BooksDatabase::class.java
        ).build()
    }

    @Provides
    @Singleton
    fun provideBooksUseCases(db : BooksDatabase): BooksUseCases {
        return BooksUseCases(
            getBooks = GetBooksUseCase(db.dao),
            getBook = GetBookUseCase(db.dao),
            upsertBook = UpsertBookUseCase(db.dao),
            deleteBook = DeleteBookUseCase(db.dao)
        )
    }
}