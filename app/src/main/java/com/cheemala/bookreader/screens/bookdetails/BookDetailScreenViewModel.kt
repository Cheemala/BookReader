package com.cheemala.bookreader.screens.bookdetails

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cheemala.bookreader.BookRepository
import com.cheemala.bookreader.model.data.Book
import com.cheemala.bookreader.model.data.DataOrException
import com.cheemala.bookreader.model.data.Item
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookDetailScreenViewModel @Inject constructor(private val repository: BookRepository) :
    ViewModel() {

    val bookSavedStatus: MutableState<Boolean> = mutableStateOf(false)

    suspend fun getBookInfo(bookId: String): DataOrException<Item, Boolean, Exception> {

        return repository.getBookInfo(bookId = bookId)

    }

    fun saveBook(book: Book) = viewModelScope.launch {

        val defferedObj = viewModelScope.async {
            repository.saveBook(book)
        }
        Log.d("book_saved_status_", "${defferedObj.await()}")
        if (defferedObj.await().toInt() > 0)
            bookSavedStatus.value = true

    }

}