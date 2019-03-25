package ua.ck.zabochen.forecastapp.data.repository

import androidx.lifecycle.LiveData
import ua.ck.zabochen.forecastapp.data.entity.weather.today.measuresystem.MeasureSystemTodayWeatherEntity

interface WeatherRepository {

    // Today Weather
    suspend fun getTodayWeather(metricSystem: Boolean): LiveData<out MeasureSystemTodayWeatherEntity>
}