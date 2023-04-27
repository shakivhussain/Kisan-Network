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
                val errorContent = response.errorBody()?.string()
                Log.d("TAG", "1 onCreate: ${response.body().toString()} ${response.message()} \n\n ${response.errorBody().toString()}, $errorContent")
                ApiResponse.Success(body)
            } else {
                val errorContent = response.errorBody()?.string()

                Log.d("TAG", " 2onCreate: ${response.body().toString()} ${response.message()} \n\n ${response.errorBody().toString()}, $errorContent")


                ApiResponse.Failure(code, "", body)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Log.d("TAG", "sendSms: ${e.localizedMessage}")

            return ApiResponse.Failure(
                status_code = -1, status_message = e.message ?: "Something went wrong!",
                results = null, throwable = e
            )
        }
    }
}