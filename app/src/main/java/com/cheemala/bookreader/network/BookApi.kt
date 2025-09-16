package com.cheemala.bookreader.network

import com.cheemala.bookreader.model.data.BookVolumeInfo
import com.cheemala.bookreader.model.data.Item
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface BookApi {

    @GET("volumes")
    suspend fun getAllBooks(@Query("q") query:String): BookVolumeInfo

    @GET("volumes/{bookId}")
    suspend fun getBookInfo(@Path("bookId") bookId:String): Item

}