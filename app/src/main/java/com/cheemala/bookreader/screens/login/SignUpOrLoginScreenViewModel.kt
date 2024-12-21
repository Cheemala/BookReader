package com.cheemala.bookreader.screens.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cheemala.bookreader.model.data.User
import com.cheemala.bookreader.model.repository.RoomRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpOrLoginScreenViewModel @Inject constructor(private val roomRepository: RoomRepository): ViewModel() {

    suspend fun signUpUser(user: User): Boolean{
        val rowId:Long = roomRepository.insertUser(user)
        return rowId > 0
    }

    suspend fun checkUserExisted(userEmail: String, userPswrd: String): Boolean {
        val userCount:Int = roomRepository.checkUserExisted(userEmail, userPswrd)
        Log.d("user_login_status_","$userCount")
        return userCount > 0
    }

}