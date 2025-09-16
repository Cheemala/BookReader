package com.cheemala.bookreader

import android.util.Log
import com.cheemala.bookreader.model.data.Book
import com.cheemala.bookreader.model.data.DataOrException
import com.cheemala.bookreader.model.data.Item
import com.cheemala.bookreader.model.datasource.dao.BookDao
import com.cheemala.bookreader.network.BookApi
import javax.inject.Inject

class BookRepository @Inject constructor(private val bookApi: BookApi, private val bookDao: BookDao) {
    private var bookDataOrException = DataOrException<List<Item>, Boolean, Exception>()
    private var bookInfoDataOrException = DataOrException<Item, Boolean, Exception>()

    suspend fun getAllBooks(query: String): DataOrException<List<Item>, Boolean, Exception> {

        try {
            bookDataOrException.loading = true
            bookDataOrException.data = bookApi.getAllBooks(query).items
            if (bookDataOrException.data!!.isNotEmpty())
                bookDataOrException.loading = false
        } catch (ex: Exception) {
            Log.d("book_ex_", ex.message.toString())
            bookDataOrException.loading = false
            bookDataOrException.exception = ex
        }
        return bookDataOrException
    }

    suspend fun getBookInfo(bookId: String): DataOrException<Item, Boolean, Exception>{
        try {
            bookInfoDataOrException.loading = true
            bookInfoDataOrException.data = bookApi.getBookInfo(bookId)
            if(bookInfoDataOrException.data!!.toString().isNotEmpty())
                bookInfoDataOrException.loading = false
        }catch (ex: Exception){
            bookInfoDataOrException.loading = false
            bookInfoDataOrException.exception = ex
        }
        return bookInfoDataOrException
    }

    suspend fun saveBook(book: Book): Long{
        return bookDao.insertBook(book)
    }

}