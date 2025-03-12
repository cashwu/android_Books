package com.cashwu.books.domain.usecase

import com.cashwu.books.FakeDatabase
import com.cashwu.books.domain.model.Book
import com.cashwu.books.utils.BookException
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

/**
 *
 * @author cash.wu
 * @since 2025/03/12
 *
 */
class UpsertBookUseCaseTest {

    lateinit var upsertBookUseCase: UpsertBookUseCase

    val database = FakeDatabase()

    @Before
    fun setUp() {
        upsertBookUseCase = UpsertBookUseCase(database)
    }

    @Test
    fun `book should be added if fields are valid`() {
        runBlocking {
            upsertBookUseCase(Book(1, "title", "author", true, 1))

            val books = database.getBooks().first()
            assertEquals(1, books.size)
        }
    }

    @Test(expected = BookException::class)
    fun `book should not be added if title is empty`() {

        runBlocking {
            upsertBookUseCase(Book(1, "", "author", true, 1))
        }
    }

    @Test(expected = BookException::class)
    fun `book should not be added if author is empty`() {

        runBlocking {
            upsertBookUseCase(Book(1, "title", "", true, 1))
        }
    }

}