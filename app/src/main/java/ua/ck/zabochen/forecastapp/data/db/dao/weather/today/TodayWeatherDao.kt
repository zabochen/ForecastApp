package ua.ck.zabochen.forecastapp.data.db.dao.weather.today

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ua.ck.zabochen.forecastapp.data.entity.weather.today.TodayWeatherEntity
import ua.ck.zabochen.forecastapp.data.entity.weather.today.measuresystem.ImperialSystemTodayWeatherEntity
import ua.ck.zabochen.forecastapp.data.entity.weather.today.measuresystem.MetricSystemTodayWeatherEntity
import ua.ck.zabochen.forecastapp.internal.TABLE_TODAY_WEATHER_COLUMN_ID
import ua.ck.zabochen.forecastapp.internal.TABLE_TODAY_WEATHER

@Dao
interface TodayWeatherDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun updateTodayWeather(todayWeatherEntity: TodayWeatherEntity)

    @Query("SELECT * FROM $TABLE_TODAY_WEATHER WHERE id = $TABLE_TODAY_WEATHER_COLUMN_ID")
    fun getTodayWeatherMetric(): LiveData<MetricSystemTodayWeatherEntity>

    @Query("SELECT * FROM $TABLE_TODAY_WEATHER WHERE id = $TABLE_TODAY_WEATHER_COLUMN_ID")
    fun getTodayWeatherImperial(): LiveData<ImperialSystemTodayWeatherEntity>
}