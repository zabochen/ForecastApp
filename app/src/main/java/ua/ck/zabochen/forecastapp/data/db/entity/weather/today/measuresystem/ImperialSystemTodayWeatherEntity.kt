package ua.ck.zabochen.forecastapp.data.db.entity.weather.today.measuresystem

import androidx.room.ColumnInfo

class ImperialSystemTodayWeatherEntity(
    @ColumnInfo(name = "tempF")
    override val temperature: Double,
    @ColumnInfo(name = "condition_text")
    override val conditionText: String,
    @ColumnInfo(name = "condition_icon")
    override val conditionIconUrl: String,
    @ColumnInfo(name = "windMph")
    override val windSpeed: Double,
    @ColumnInfo(name = "feelslikeF")
    override val feelsLikeTemperature: Double
) : MeasureSystemTodayWeatherEntity