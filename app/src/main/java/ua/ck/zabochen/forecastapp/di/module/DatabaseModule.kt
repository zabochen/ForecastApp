package ua.ck.zabochen.forecastapp.di.module

import android.content.Context
import dagger.Module
import dagger.Provides
import ua.ck.zabochen.forecastapp.data.db.WeatherDatabase
import ua.ck.zabochen.forecastapp.data.db.dao.weather.today.TodayWeatherDao
import javax.inject.Singleton

@Module(includes = [AppModule::class])
class DatabaseModule {

    @Singleton
    @Provides
    fun provideWeatherDatabase(context: Context): WeatherDatabase {
        return WeatherDatabase(context)
    }

    @Singleton
    @Provides
    fun provideTodayWeatherDao(weatherDatabase: WeatherDatabase): TodayWeatherDao {
        return weatherDatabase.todayWeatherDao()
    }
}