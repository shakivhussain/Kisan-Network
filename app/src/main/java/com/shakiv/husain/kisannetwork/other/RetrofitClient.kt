package com.shakiv.husain.kisannetwork.other

import com.shakiv.husain.kisannetwork.AppConstants
import okhttp3.Credentials
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private const val BASE_URL = "https://api.twilio.com/2010-04-01/Accounts/{${AppConstants.ACCOUNT_SID}}/"

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor { chain ->
            val original = chain.request()

            val builder = original.newBuilder()
                .header("Authorization", Credentials.basic(AppConstants.ACCOUNT_SID, AppConstants.AUTH_TOKEN))
            val request = builder.build()
            chain.proceed(request)
        }
        .build()

    val instance: TwilloApi by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

        retrofit.create(TwilloApi::class.java)
    }
}
