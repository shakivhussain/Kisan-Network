package com.shakiv.husain.kisannetwork.data.model

import java.io.Serializable

data class Contact(
    val id: Int,
    val firstName: String,
    val lastName: String,
    val number: String
):Serializable
