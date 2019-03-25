package ua.ck.zabochen.forecastapp.di.module

import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val context: Context) {

    @Singleton
    @Provides
    fun provideAppContext(): Context {
        return context.applicationContext
    }
}