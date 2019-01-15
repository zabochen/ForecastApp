package ua.ck.zabochen.forecastapp.data.network

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
import ua.ck.zabochen.forecastapp.data.network.response.weather.current.TodayWeatherResponse

const val API_KEY: String = "9ab5fb2053854c85a2c73427191301"

// http://api.apixu.com/v1/current.json?key=9ab5fb2053854c85a2c73427191301&q=cherkasy

interface ApixuWeatherApiService {

    @GET("current.json")
    fun getCurrentWeather(
        @Query("q") location: String,
        @Query("lang") languageCode: String = "en"
    ): Deferred<TodayWeatherResponse>

    companion object {
        operator fun invoke(): ApixuWeatherApiService {
            val requestInterceptor = Interceptor { chain ->
                val updatedUrl: HttpUrl = chain.request().url().newBuilder()
                    .addQueryParameter("key", API_KEY)
                    .build()

                val updatedRequest: Request = chain.request().newBuilder()
                    .url(updatedUrl).build()

                chain.proceed(updatedRequest)
            }

            val okHttpClient = OkHttpClient.Builder()
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