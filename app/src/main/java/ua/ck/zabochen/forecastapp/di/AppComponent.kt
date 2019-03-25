package ua.ck.zabochen.forecastapp.di

import dagger.Component
import ua.ck.zabochen.forecastapp.di.module.*
import ua.ck.zabochen.forecastapp.ui.weather.today.TodayWeatherFragment
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        DatabaseModule::class,
        NetworkModule::class,
        RepositoryModule::class,
        ViewModelFactoryModule::class
    ]
)
interface AppComponent {

    // TodayWeather Fragment
    fun inject(todayWeatherFragment: TodayWeatherFragment)
}