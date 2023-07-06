package com.cst.taipeitour.data.network

import com.cst.taipeitour.data.network.response.ResponseData
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface ApiService {

    companion object{

        private const val baseUrl = "https://www.travel.taipei/open-api/"

        operator fun invoke(
            networkConnectionInterceptor: NetworkConnectionInterceptor
        ) : ApiService {

            val okkHttpclient = OkHttpClient.Builder()
                .addInterceptor(networkConnectionInterceptor)
                .build()

            return Retrofit.Builder()
                .client(okkHttpclient)
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiService::class.java)
        }
    }

    @Headers("Accept: application/json")
    @GET("{lang}/Attractions/All")
    fun getList(@Path("lang") lang: String): Call<ResponseData>
}