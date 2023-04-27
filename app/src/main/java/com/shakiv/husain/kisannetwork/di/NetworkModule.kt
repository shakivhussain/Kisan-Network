package com.shakiv.husain.kisannetwork.di

import com.shakiv.husain.kisannetwork.utils.AppConstants
import com.shakiv.husain.kisannetwork.data.api.AuthService
import dagger.Module
import dagger.Provides
import okhttp3.Credentials
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(AppConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor { chain ->
            val original = chain.request()
            val builder = original.newBuilder()
                .header(
                    "Authorization",
                    Credentials.basic(AppConstants.ACCOUNT_SID, AppConstants.AUTH_TOKEN)
                )
            val request = builder.build()
            chain.proceed(request)
        }
        .build()

    @Singleton
    @Provides
    fun provideNetworkService(retrofit: Retrofit): AuthService {
        return retrofit.create(AuthService::class.java)
    }

}