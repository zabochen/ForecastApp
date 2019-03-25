package ua.ck.zabochen.forecastapp.ui.weather.today

import androidx.lifecycle.ViewModel
import ua.ck.zabochen.forecastapp.data.repository.WeatherRepository
import ua.ck.zabochen.forecastapp.internal.UnitSystem
import ua.ck.zabochen.forecastapp.internal.lazyDeferred

class TodayWeatherViewModel(
    private val weatherRepository: WeatherRepository
) : ViewModel() {

    private val unitSystem = UnitSystem.METRIC

    val isMetric: Boolean
        get() = unitSystem == UnitSystem.METRIC

    val todayWeather by lazyDeferred {
        weatherRepository.getTodayWeather(isMetric)
    }
}