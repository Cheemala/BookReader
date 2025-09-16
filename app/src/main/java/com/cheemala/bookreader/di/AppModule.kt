package com.cheemala.bookreader.di

import android.content.Context
import androidx.room.Room
import com.cheemala.bookreader.BookRepository
import com.cheemala.bookreader.network.BookApi
import com.cheemala.bookreader.model.datasource.BookReaderDatabase
import com.cheemala.bookreader.model.datasource.dao.BookDao
import com.cheemala.bookreader.util.AppConstants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideBookReaderDatabase(@ApplicationContext appContext: Context) =
        Room.databaseBuilder(context = appContext, BookReaderDatabase::class.java, "book_reader_db")
            .build()

    @Provides
    @Singleton
    fun provideUserDao(bookReaderDatabase: BookReaderDatabase) = bookReaderDatabase.userDao()

    @Provides
    @Singleton
    fun provideBookDao(bookReaderDatabase: BookReaderDatabase) = bookReaderDatabase.bookDao()

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(AppConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideBookApi(retrofit: Retrofit): BookApi = retrofit.create(BookApi::class.java)

    @Provides
    @Singleton
    fun provideBookRepository(bookApi: BookApi, bookDao: BookDao): BookRepository = BookRepository(bookApi, bookDao)

}