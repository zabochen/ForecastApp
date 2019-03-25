package ua.ck.zabochen.forecastapp.data.entity.weather.today.measuresystem

import androidx.room.ColumnInfo

data class MetricSystemTodayWeatherEntity(
    @ColumnInfo(name = "tempC")
    override val temperature: Double,

    @ColumnInfo(name = "condition_text")
    override val conditionText: String,

    @ColumnInfo(name = "condition_icon")
    override val conditionIconUrl: String,

    @ColumnInfo(name = "windKph")
    override val windSpeed: Double,

    @ColumnInfo(name = "feelslikeC")
    override val feelsLikeTemperature: Double
) : MeasureSystemTodayWeatherEntity