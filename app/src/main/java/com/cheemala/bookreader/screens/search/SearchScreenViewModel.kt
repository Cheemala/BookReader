package com.cheemala.bookreader.screens.search

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cheemala.bookreader.BookRepository
import com.cheemala.bookreader.model.data.DataOrException
import com.cheemala.bookreader.model.data.Item
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchScreenViewModel @Inject constructor(private val bookRepository: BookRepository): ViewModel() {

   /* var searchedBookInfoState :MutableState<DataOrException<List<Item>, Boolean, Exception>>
            = mutableStateOf(DataOrException(data = null, loading = true, exception = null))
*/
    // Backing property (mutable inside VM)
    val searchedBookInfoState = mutableStateOf(DataOrException<List<Item>, Boolean, Exception>())
    // Public immutable state for the UI
    // val searchedBookInfoState: State<DataOrException<List<Item>, Boolean, Exception>> = _searchedBookInfoState


    init {
        searchBooks("android")
    }

    fun searchBooks(query: String) {

        viewModelScope.launch {

            if(query.isEmpty())
                return@launch

            try {
                searchedBookInfoState.value = DataOrException(data = null, loading = true, exception = null)
                val result = bookRepository.getAllBooks(query)
                Log.d("book_data_vm_", result.data.toString())
                searchedBookInfoState.value = DataOrException(data = result.data, loading = false, exception = null)
            }catch (ex: Exception){
                searchedBookInfoState.value = DataOrException(data = null, loading = false, exception = ex)
                Log.d("book_data_ex_", ex.toString())
            }

        }

    }

}