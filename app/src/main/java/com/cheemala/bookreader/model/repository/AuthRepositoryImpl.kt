package com.cheemala.bookreader.model.repository

import com.cheemala.bookreader.model.data.UserEntity
import com.cheemala.bookreader.model.datasource.dao.BookDao
import com.cheemala.bookreader.model.datasource.dao.UserDao
import javax.inject.Inject

class RoomRepository @Inject constructor(
    private val userDao: UserDao,
    private val bookDao: BookDao
) {
    suspend fun insertUser(userEntity: UserEntity): Long = userDao.insertUser(userEntity)

    suspend fun updateUser(userEntity: UserEntity) = userDao.updateUser(userEntity)

    suspend fun checkUserExisted(userEmail: String, userPswrd: String) =
        userDao.checkUserExisted(userEmail, userPswrd)

    suspend fun getCurrentUserId(userEmail: String, userPswrd: String) =
        userDao.getCurrentUserId(userEmail, userPswrd)
}