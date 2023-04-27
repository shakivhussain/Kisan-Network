package com.shakiv.husain.kisannetwork.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.shakiv.husain.kisannetwork.data.model.Contact


@Database(entities = [Contact::class], version = 1)
abstract class ContactDatabase : RoomDatabase(){
    abstract fun getContactDao() : ContactDao

}