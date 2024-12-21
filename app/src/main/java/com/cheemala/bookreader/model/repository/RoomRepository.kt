package com.cheemala.bookreader.model.repository

import com.cheemala.bookreader.model.data.User
import com.cheemala.bookreader.model.datasource.BookReaderDatabase
import javax.inject.Inject

class RoomRepository @Inject constructor(private val bookReaderDatabase: BookReaderDatabase) {
    suspend fun insertUser(user: User): Long = bookReaderDatabase.userDao().insertUser(user)

    suspend fun updateUser(user: User) = bookReaderDatabase.userDao().updateUser(user)

    suspend fun checkUserExisted(userEmail: String, userPswrd: String) = bookReaderDatabase.userDao().checkUserExisted(userEmail,userPswrd)
}