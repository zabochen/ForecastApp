package ua.ck.zabochen.forecastapp.data.repository

import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.threeten.bp.ZonedDateTime
import ua.ck.zabochen.forecastapp.data.db.dao.weather.today.TodayWeatherDao
import ua.ck.zabochen.forecastapp.data.entity.weather.today.TodayWeatherResponse
import ua.ck.zabochen.forecastapp.data.entity.weather.today.measuresystem.MeasureSystemTodayWeatherEntity
import ua.ck.zabochen.forecastapp.data.network.WeatherNetwork
import java.util.*

class WeatherRepositoryImpl(
    private val todayWeatherDao: TodayWeatherDao,
    private val weatherNetwork: WeatherNetwork
) : WeatherRepository {

    init {
        weatherNetwork.downloadedTodayWeather.observeForever { newTodayWeatherResponse ->
            persistTodayWeather(newTodayWeatherResponse)
        }
    }

    private suspend fun initTodayWeather() {
        if (isFetchTodayWeatherNeeded(ZonedDateTime.now().minusHours(1))) {
            fetchTodayWeather()
        }
    }

    private fun isFetchTodayWeatherNeeded(lastFetchTime: ZonedDateTime): Boolean {
        val thirtyMinutesAgo = ZonedDateTime.now().minusMinutes(30)
        return lastFetchTime.isBefore(thirtyMinutesAgo)
    }

    private suspend fun fetchTodayWeather() {
        weatherNetwork.fetchTodayWeather(
            city = "Kiev",
            language = Locale.getDefault().language
        )
    }

    private fun persistTodayWeather(todayWeatherResponse: TodayWeatherResponse) {
        GlobalScope.launch(Dispatchers.IO) {
            todayWeatherDao.updateTodayWeather(todayWeatherResponse.todayWeatherEntity)
        }
    }

    override suspend fun getTodayWeather(metricSystem: Boolean): LiveData<out MeasureSystemTodayWeatherEntity> {
        return withContext(Dispatchers.IO) {
            initTodayWeather()
            return@withContext if (metricSystem) {
                todayWeatherDao.getTodayWeatherMetric()
            } else {
                todayWeatherDao.getTodayWeatherImperial()
            }
        }
    }
}