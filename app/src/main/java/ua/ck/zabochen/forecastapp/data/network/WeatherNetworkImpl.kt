package ua.ck.zabochen.forecastapp.data.network

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import ua.ck.zabochen.forecastapp.data.entity.weather.today.TodayWeatherResponse
import ua.ck.zabochen.forecastapp.data.network.service.ApixuWeatherApiService
import ua.ck.zabochen.forecastapp.internal.NoConnectionException

class WeatherNetworkImpl(private val apixuWeatherApiService: ApixuWeatherApiService) : WeatherNetwork, AnkoLogger {

    private val _downloadedTodayWeather = MutableLiveData<TodayWeatherResponse>()
    override val downloadedTodayWeather: LiveData<TodayWeatherResponse>
        get() = _downloadedTodayWeather

    override suspend fun fetchTodayWeather(city: String, language: String) {
        try {
            val fetchedTodayWeather = apixuWeatherApiService.getCurrentWeatherAsync(city, language)
                .await()
            _downloadedTodayWeather.postValue(fetchedTodayWeather)
        } catch (e: NoConnectionException) {
            info { "--===::: NoConnectionException :::===-- ${e.printStackTrace()}" }
        }
    }
}