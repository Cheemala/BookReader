package com.cheemala.bookreader.di

import android.content.Context
import androidx.room.Room
import com.cheemala.bookreader.model.datasource.BookReaderDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideBookReaderDatabase(@ApplicationContext appContext: Context)
    = Room.databaseBuilder(context = appContext, BookReaderDatabase::class.java, "book_reader_db").build()

    @Provides
    @Singleton
    fun provideUserDao(bookReaderDatabase: BookReaderDatabase) = bookReaderDatabase.userDao()

}