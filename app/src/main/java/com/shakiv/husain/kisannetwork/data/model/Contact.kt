package com.shakiv.husain.kisannetwork.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class Contact(
    @ColumnInfo(name = "id") @PrimaryKey(autoGenerate = false) val id: Int,
    @ColumnInfo(name = "firstName") val firstName: String,
    @ColumnInfo(name = "lastName")val lastName: String,
    @ColumnInfo(name = "number") val number: String,
    @ColumnInfo(name = "openedAt") var openedAt: Long = System.currentTimeMillis()
) : Serializable
