package com.shakiv.husain.kisannetwork.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.shakiv.husain.kisannetwork.data.model.Contact

@Dao
interface ContactDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addContact(contact: Contact)

    @Query("SELECT * FROM contact ORDER BY openedAt DESC ")
    suspend fun getAllContacts(): List<Contact>

}