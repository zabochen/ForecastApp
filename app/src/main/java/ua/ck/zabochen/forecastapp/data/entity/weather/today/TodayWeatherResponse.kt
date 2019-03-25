package ua.ck.zabochen.forecastapp.data.entity.weather.today

import com.google.gson.annotations.SerializedName

data class TodayWeatherResponse(
    val location: Location,
    @SerializedName("current")
    val todayWeatherEntity: TodayWeatherEntity
)