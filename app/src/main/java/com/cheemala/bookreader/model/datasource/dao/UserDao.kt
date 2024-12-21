package com.cheemala.bookreader.model.datasource.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.TypeConverter
import androidx.room.Update
import com.cheemala.bookreader.model.data.User
import kotlinx.coroutines.Deferred

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: User): Long

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateUser(user: User)

    @Query("SELECT COUNT(*) FROM user_tbl where user_email = :userEmail AND user_pswrd = :userPswrd")
    suspend fun checkUserExisted(userEmail: String, userPswrd: String): Int

}