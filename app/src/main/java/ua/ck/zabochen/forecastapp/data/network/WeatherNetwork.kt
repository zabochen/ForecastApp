package ua.ck.zabochen.forecastapp.data.network

import androidx.lifecycle.LiveData
import ua.ck.zabochen.forecastapp.data.network.response.weather.today.TodayWeatherResponse

interface WeatherNetwork {
    val downloadedTodayWeather: LiveData<TodayWeatherResponse>
    suspend fun fetchTodayWeather(city: String, language: String)
}