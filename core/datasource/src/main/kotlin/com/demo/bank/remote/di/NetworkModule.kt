package com.demo.bank.remote.di

import com.demo.bank.remote.NetworkDataSource
import com.demo.bank.remote.RetrofitNetwork
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.Call
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

        @Provides
        @Singleton
        fun provideNetworkDataSource(retrofitNetwork: RetrofitNetwork) : NetworkDataSource = retrofitNetwork


        @Provides
        @Singleton
        fun providesNetworkJson(): Json = Json {
            ignoreUnknownKeys = true
        }


        @Provides
        @Singleton
        fun okHttpCallFactory(): Call.Factory = OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor().apply {
                        setLevel(HttpLoggingInterceptor.Level.BODY)
                }
            )
            .build()


}