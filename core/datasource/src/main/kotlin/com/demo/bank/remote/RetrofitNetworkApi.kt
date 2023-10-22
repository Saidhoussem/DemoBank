package com.demo.bank.remote

import okhttp3.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Retrofit API declaration
 */
private interface RetrofitNetworkApi {
    @GET("banks.json")
    suspend fun getBanks(
    ): List<BankResponse>

}

private const val BASE_URL = "https://cdf-test-mobile-default-rtdb.europe-west1.firebasedatabase.app/"

@Singleton
class RetrofitNetwork @Inject constructor(
    okhttpCallFactory: Call.Factory,
) : NetworkDataSource {

    private val networkApi = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .callFactory(okhttpCallFactory)
        .addConverterFactory(
            GsonConverterFactory.create()
        )
        .build()
        .create(RetrofitNetworkApi::class.java)

    override suspend fun getBanks() : List<BankResponse> {

        return networkApi.getBanks()
    }

}