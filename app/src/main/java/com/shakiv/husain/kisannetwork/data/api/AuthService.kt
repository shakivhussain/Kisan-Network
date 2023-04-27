package com.shakiv.husain.kisannetwork.data.api

import com.shakiv.husain.kisannetwork.data.model.BaseResponse
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface AuthService {

    @FormUrlEncoded
    @POST("Messages")
    suspend fun sendMessage(
        @Field("From") from: String,
        @Field("To") to: String,
        @Field("Body") body: String,
    ): Response<BaseResponse<String>>
}