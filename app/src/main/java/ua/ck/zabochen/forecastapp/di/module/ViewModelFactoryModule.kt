package ua.ck.zabochen.forecastapp.di.module

import dagger.Module
import dagger.Provides
import ua.ck.zabochen.forecastapp.data.repository.WeatherRepository
import ua.ck.zabochen.forecastapp.ui.weather.today.TodayWeatherViewModelFactory

@Module(includes = [RepositoryModule::class])
class ViewModelFactoryModule {

    @Provides
    fun provideTodayWeatherViewModelFactory(weatherRepository: WeatherRepository): TodayWeatherViewModelFactory {
        return TodayWeatherViewModelFactory(weatherRepository)
    }
}