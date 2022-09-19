package com.example.weatherapplication

import com.example.weatherapplication.model.WeatherDetailsResponseModel
import com.example.weatherapplication.utils.Constants
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query
import retrofit2.http.Url

interface ApiService {

    @Headers("Content-Type: application/json")
    @GET("forecast/daily")
    fun getWeatherDetailsList(
        @Query(value = "lon") lon: Double,
        @Query(value = "lat") lat: Double,
        @Query(value = "key") key: String = Constants.ACCESS_KEY
    ): Call<WeatherDetailsResponseModel>
}