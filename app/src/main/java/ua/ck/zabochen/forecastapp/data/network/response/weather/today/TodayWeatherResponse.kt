package ua.ck.zabochen.forecastapp.data.network.response.weather.today

import com.google.gson.annotations.SerializedName
import ua.ck.zabochen.forecastapp.data.db.entity.weather.today.TodayWeatherEntity
import ua.ck.zabochen.forecastapp.data.db.entity.weather.today.Location

data class TodayWeatherResponse(
    val location: Location,
    @SerializedName("current")
    val todayWeatherEntity: TodayWeatherEntity
)