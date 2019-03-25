package ua.ck.zabochen.forecastapp.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ua.ck.zabochen.forecastapp.data.db.dao.weather.today.TodayWeatherDao
import ua.ck.zabochen.forecastapp.data.entity.weather.today.TodayWeatherEntity
import ua.ck.zabochen.forecastapp.internal.DATABASE_NAME
import ua.ck.zabochen.forecastapp.internal.DATABASE_VERSION

@Database(
    entities = [TodayWeatherEntity::class],
    version = DATABASE_VERSION
)
abstract class WeatherDatabase : RoomDatabase() {
    abstract fun todayWeatherDao(): TodayWeatherDao

    // TODO: Database: Volatile, synchronized, also
    companion object {

        @Volatile
        private var instance: WeatherDatabase? = null
        private val lock = Any()

        operator fun invoke(context: Context): WeatherDatabase {
            return instance ?: synchronized<WeatherDatabase>(lock) {
                return instance ?: buildWeatherDatabase(context).also { instance = it }
            }
        }

        private fun buildWeatherDatabase(context: Context): WeatherDatabase {
            return Room.databaseBuilder(context.applicationContext, WeatherDatabase::class.java, DATABASE_NAME)
                .build()
        }
    }
}