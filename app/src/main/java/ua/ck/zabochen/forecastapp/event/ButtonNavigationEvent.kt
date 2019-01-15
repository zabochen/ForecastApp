package ua.ck.zabochen.forecastapp.event

import androidx.fragment.app.Fragment
import ua.ck.zabochen.forecastapp.ui.settings.SettingsFragment
import ua.ck.zabochen.forecastapp.ui.weather.today.TodayWeatherFragment
import ua.ck.zabochen.forecastapp.ui.weather.week.WeekWeatherFragment

sealed class ButtonNavigationEvent {

    abstract fun fragment(): Fragment

    object TodayWeather : ButtonNavigationEvent() {
        override fun fragment(): Fragment {
            return TodayWeatherFragment.newInstance()
        }
    }

    object WeekWeather : ButtonNavigationEvent() {
        override fun fragment(): Fragment {
            return WeekWeatherFragment.newInstance()
        }
    }

    object Settings : ButtonNavigationEvent() {
        override fun fragment(): Fragment {
            return SettingsFragment.newInstance()
        }
    }
}