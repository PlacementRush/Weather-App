package com.example.weatherapp

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

class Api {
    companion object{
        private val url = "https://api.openweathermap.org/data/2.5/"

        val dataService: DataService? = null

        private val retrofit = Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

        fun<T> buildService(service: Class<T>): T{
            return retrofit.create(service)
        }
    }
}

interface DataService{
    @GET("weather")
    fun weatherInfo(@Query("q") city: String,
                    @Query("units") units: String,
                    @Query("appid") appid: String): Call<WeatherData>
}