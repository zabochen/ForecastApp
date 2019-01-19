package ua.ck.zabochen.forecastapp.data.db.entity.weather.today.measuresystem

interface MeasureSystemTodayWeatherEntity {
    val temperature: Double
    val conditionText: String
    val conditionIconUrl: String
    val windSpeed: Double
    val feelsLikeTemperature: Double
}