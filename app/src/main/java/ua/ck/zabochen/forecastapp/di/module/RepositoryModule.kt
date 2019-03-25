package ua.ck.zabochen.forecastapp.di.module

import dagger.Module
import dagger.Provides
import ua.ck.zabochen.forecastapp.data.db.dao.weather.today.TodayWeatherDao
import ua.ck.zabochen.forecastapp.data.network.WeatherNetwork
import ua.ck.zabochen.forecastapp.data.repository.WeatherRepository
import ua.ck.zabochen.forecastapp.data.repository.WeatherRepositoryImpl
import javax.inject.Singleton

@Module(includes = [AppModule::class, DatabaseModule::class, NetworkModule::class])
class RepositoryModule {

    @Singleton
    @Provides
    fun provideWeatherRepository(todayWeatherDao: TodayWeatherDao, weatherNetwork: WeatherNetwork): WeatherRepository {
        return WeatherRepositoryImpl(todayWeatherDao, weatherNetwork)
    }
}