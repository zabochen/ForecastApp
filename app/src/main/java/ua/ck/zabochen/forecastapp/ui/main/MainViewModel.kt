package ua.ck.zabochen.forecastapp.ui.main

import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ua.ck.zabochen.forecastapp.R
import ua.ck.zabochen.forecastapp.ui.settings.SettingsFragment
import ua.ck.zabochen.forecastapp.ui.weather.today.TodayWeatherFragment
import ua.ck.zabochen.forecastapp.ui.weather.week.WeekWeatherFragment

class MainViewModel : ViewModel() {

    private var _fragmentContainerState = MutableLiveData<Fragment>()
    val fragmentContainerState: LiveData<Fragment>
        get() = _fragmentContainerState

    fun selectedBottomNavigationItem(itemId: Int) {
        when (itemId) {
            R.id.menuBottomNavigation_itemTodayWeather -> _fragmentContainerState.postValue(TodayWeatherFragment.newInstance())
            R.id.menuBottomNavigation_itemWeekWeather -> _fragmentContainerState.postValue(WeekWeatherFragment.newInstance())
            R.id.menuBottomNavigation_itemSettings -> _fragmentContainerState.postValue(SettingsFragment.newInstance())
        }
    }
}