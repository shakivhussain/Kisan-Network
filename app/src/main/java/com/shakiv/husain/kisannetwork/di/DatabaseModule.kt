package com.shakiv.husain.kisannetwork.di

import android.content.Context
import androidx.room.Room
import com.shakiv.husain.kisannetwork.utils.AppConstants
import com.shakiv.husain.kisannetwork.data.db.ContactDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {
    @Singleton
    @Provides
    fun provideDatabase(context: Context): ContactDatabase {
        return Room.databaseBuilder(context, ContactDatabase::class.java, AppConstants.DATABASE_NAME).build()
    }

}