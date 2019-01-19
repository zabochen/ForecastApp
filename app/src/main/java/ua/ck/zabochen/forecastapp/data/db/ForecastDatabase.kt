package ua.ck.zabochen.forecastapp.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import ua.ck.zabochen.forecastapp.data.db.dao.weather.today.TodayWeatherDao
import ua.ck.zabochen.forecastapp.data.db.entity.weather.today.TodayWeatherEntity
import ua.ck.zabochen.forecastapp.utils.DATABASE_VERSION

@Database(
    entities = [TodayWeatherEntity::class],
    version = DATABASE_VERSION
)
abstract class ForecastDatabase : RoomDatabase() {
    abstract fun todayWeatherDao(): TodayWeatherDao
}