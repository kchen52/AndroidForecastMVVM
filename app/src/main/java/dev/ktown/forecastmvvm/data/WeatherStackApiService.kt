package dev.ktown.forecastmvvm.data

import dev.ktown.forecastmvvm.data.response.CurrentWeatherResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

const val API_KEY = "5a490c5f89a3621ec06d10f20f4840f8"

// http://api.weatherstack.com/current?access_key=5a490c5f89a3621ec06d10f20f4840f8&query=New%20York&lang=en

interface WeatherStackApiService {
    @GET("current.json")
    fun getCurrentWeather(
        @Query("query") location: String,
        @Query("lang") languageCode: String = "en"
    ): Deferred<CurrentWeatherResponse>
}