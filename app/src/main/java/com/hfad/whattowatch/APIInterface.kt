package com.hfad.whattowatch

import com.hfad.whattowatch.API.SearchResult
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query
interface APIInterface {
    @GET("search")
    @Headers("X-RapidAPI-Key:832c38ddcfmshee53ce9983b62bfp187fd9jsn18973a142442", "X-RapidAPI-Host:streaming-availability.p.rapidapi.com")
    fun getSearch(@Query("title") search:String, @Query("country") type: String): Call<SearchResult??>?

    companion object{
        var BASE_URL = "https://streaming-availability.p.rapidapi.com/"

        fun create() : APIInterface{
            val logging = HttpLoggingInterceptor()
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)
            val httpClient = OkHttpClient.Builder()
            httpClient.addInterceptor(logging)
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .client(httpClient.build())
                .build()
            return retrofit.create(APIInterface::class.java)
        }
    }


}