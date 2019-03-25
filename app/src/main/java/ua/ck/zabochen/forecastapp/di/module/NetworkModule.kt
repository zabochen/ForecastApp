package ua.ck.zabochen.forecastapp.di.module

import android.content.Context
import dagger.Module
import dagger.Provides
import ua.ck.zabochen.forecastapp.data.network.WeatherNetwork
import ua.ck.zabochen.forecastapp.data.network.WeatherNetworkImpl
import ua.ck.zabochen.forecastapp.data.network.interceptor.connection.ConnectionStateInterceptor
import ua.ck.zabochen.forecastapp.data.network.interceptor.connection.ConnectionStateInterceptorImpl
import ua.ck.zabochen.forecastapp.data.network.service.ApixuWeatherApiService
import javax.inject.Singleton

@Module(includes = [AppModule::class])
class NetworkModule {

    @Singleton
    @Provides
    fun provideConnectionStateInterceptor(context: Context): ConnectionStateInterceptor {
        return ConnectionStateInterceptorImpl(context)
    }

    @Singleton
    @Provides
    fun provideApixuWeatherApiService(connectionStateInterceptor: ConnectionStateInterceptor): ApixuWeatherApiService {
        return ApixuWeatherApiService(connectionStateInterceptor)
    }

    @Singleton
    @Provides
    fun provideWeatherNetwork(apixuWeatherApiService: ApixuWeatherApiService): WeatherNetwork {
        return WeatherNetworkImpl(apixuWeatherApiService)
    }
}