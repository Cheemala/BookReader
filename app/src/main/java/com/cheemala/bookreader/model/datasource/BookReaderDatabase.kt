package com.cheemala.bookreader.model.datasource

import androidx.room.Database
import androidx.room.RoomDatabase
import com.cheemala.bookreader.model.data.User
import com.cheemala.bookreader.model.datasource.dao.UserDao

@Database(entities = [User::class], version = 1)
abstract class BookReaderDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao

}