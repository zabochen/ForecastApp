package ua.ck.zabochen.forecastapp.ui.weather.today

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ua.ck.zabochen.forecastapp.data.repository.WeatherRepository

class TodayWeatherViewModelFactory(
    private val weatherRepository: WeatherRepository
): ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return TodayWeatherViewModel(weatherRepository) as T
    }
}