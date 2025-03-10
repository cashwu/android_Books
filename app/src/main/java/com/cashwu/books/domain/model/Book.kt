package com.cashwu.books.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 *
 * @author cash.wu
 * @since 2025/03/10
 *
 */
@Entity(tableName = "books")
data class Book(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    val title : String,
    val author: String,
    val read: Boolean,
    val bookType: Int
)