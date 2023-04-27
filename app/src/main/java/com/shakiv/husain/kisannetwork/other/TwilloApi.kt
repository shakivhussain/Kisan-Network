package com.shakiv.husain.kisannetwork.other

import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface TwilloApi {
    @FormUrlEncoded
    @POST("Messages")
    suspend fun sendMessage(
        @Field("From") from: String,
        @Field("To") to: String,
        @Field("Body") body: String
    ): Response<Void>

}