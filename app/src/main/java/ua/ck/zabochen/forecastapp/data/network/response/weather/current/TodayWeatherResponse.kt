package ua.ck.zabochen.forecastapp.data.network.response.weather.current

import com.google.gson.annotations.SerializedName
import ua.ck.zabochen.forecastapp.data.db.entity.TodayWeatherEntry
import ua.ck.zabochen.forecastapp.data.db.entity.Location

data class TodayWeatherResponse(
    val location: Location,
    @SerializedName("current")
    val todayWeatherEntry: TodayWeatherEntry
)