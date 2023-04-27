package com.shakiv.husain.kisannetwork.data.network

import android.util.Log
import org.json.JSONObject
import retrofit2.Response

object NetworkRequest {
    suspend fun <T> process(api: suspend () -> Response<T>): ApiResponse<T> {
        try {
            val response = api()
            val code = response.code()
            val body = response.body()
            val errorBody = response.errorBody()
            return if (response.isSuccessful) {
                ApiResponse.Success(body)
            } else {
                ApiResponse.Failure(code, "", body)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            return ApiResponse.Failure(
                status_code = -1, status_message = e.message ?: "Something went wrong!",
                results = null, throwable = e
            )
        }
    }
}