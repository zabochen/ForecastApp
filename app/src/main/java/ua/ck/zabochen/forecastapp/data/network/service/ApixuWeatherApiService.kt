package ua.ck.zabochen.forecastapp.data.network.service

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import ua.ck.zabochen.forecastapp.data.network.interceptor.connection.ConnectionStateInterceptor
import ua.ck.zabochen.forecastapp.data.network.response.weather.today.TodayWeatherResponse
import ua.ck.zabochen.forecastapp.utils.APIXU_API_KEY

// Request example
// https://api.apixu.com/v1/current.json?key=9ab5fb2053854c85a2c73427191301&q=cherkasy

interface ApixuWeatherApiService {

    @GET("current.json")
    fun getCurrentWeather(
        @Query("q") city: String,
        @Query("lang") language: String = "en"
    ): Deferred<TodayWeatherResponse>

    companion object {
        operator fun invoke(
            connectionStateInterceptor: ConnectionStateInterceptor
        ): ApixuWeatherApiService {
            val requestInterceptor = Interceptor { chain ->
                val updatedUrl: HttpUrl = chain.request().url().newBuilder()
                    .addQueryParameter("key", APIXU_API_KEY)
                    .build()

                val updatedRequest: Request = chain.request().newBuilder()
                    .url(updatedUrl).build()

                chain.proceed(updatedRequest)
            }

            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(connectionStateInterceptor)
                .addInterceptor(requestInterceptor)
                .build()

            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("https://api.apixu.com/v1/")
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApixuWeatherApiService::class.java)
        }
    }
}