package vk.expencive.itunesalbumssearch.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object ApiClient {
    val BASE_URL = "https://itunes.apple.com/"


    fun getApiClient(): Retrofit = Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

}