package com.cheemala.bookreader.model.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "saved_books_tbl")
data class Book(
    @PrimaryKey
    @ColumnInfo(name = "user_id")
    val bookId: String,
    @ColumnInfo(name = "book_title")
    val bookTitle: String?,
    @ColumnInfo(name = "book_authors")
    val bookAuthors: String,
    @ColumnInfo(name = "book_desc")
    val bookDesc: String,
    @ColumnInfo(name = "book_rating")
    val rating: Float
)
