package ua.ck.zabochen.forecastapp

import android.app.Application
import com.jakewharton.threetenabp.AndroidThreeTen
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton
import ua.ck.zabochen.forecastapp.data.db.WeatherDatabase
import ua.ck.zabochen.forecastapp.data.network.WeatherNetwork
import ua.ck.zabochen.forecastapp.data.network.WeatherNetworkImpl
import ua.ck.zabochen.forecastapp.data.network.interceptor.connection.ConnectionStateInterceptor
import ua.ck.zabochen.forecastapp.data.network.interceptor.connection.ConnectionStateInterceptorImpl
import ua.ck.zabochen.forecastapp.data.network.service.ApixuWeatherApiService
import ua.ck.zabochen.forecastapp.data.repository.WeatherRepository
import ua.ck.zabochen.forecastapp.data.repository.WeatherRepositoryImpl

class MainApp : Application(), KodeinAware {

    override val kodein: Kodein = Kodein.lazy {
        import(androidXModule(this@MainApp))

        // Database
        bind() from singleton { WeatherDatabase(instance()) }
        bind() from singleton { instance<WeatherDatabase>().todayWeatherDao() }

        // Network
        bind<ConnectionStateInterceptor>() with singleton { ConnectionStateInterceptorImpl(instance()) }
        bind() from singleton { ApixuWeatherApiService(instance()) }
        bind<WeatherNetwork>() with singleton { WeatherNetworkImpl(instance()) }

        // Repository
        bind<WeatherRepository>() with singleton { WeatherRepositoryImpl(instance(), instance()) }

        // Fragments
    }

    override fun onCreate() {
        super.onCreate()
        setThreeTen()
    }

    private fun setThreeTen() {
        AndroidThreeTen.init(this)
    }
}