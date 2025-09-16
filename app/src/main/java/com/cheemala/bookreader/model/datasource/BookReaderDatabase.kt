package com.cheemala.bookreader.model.datasource

import androidx.room.Database
import androidx.room.RoomDatabase
import com.cheemala.bookreader.model.data.Book
import com.cheemala.bookreader.model.data.User
import com.cheemala.bookreader.model.datasource.dao.BookDao
import com.cheemala.bookreader.model.datasource.dao.UserDao

@Database(entities = [User::class, Book::class], version = 1, exportSchema = false)
abstract class BookReaderDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao

    abstract fun bookDao(): BookDao

}