package ua.ck.zabochen.forecastapp.ui.main

import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ua.ck.zabochen.forecastapp.R
import ua.ck.zabochen.forecastapp.ui.settings.SettingsFragment
import ua.ck.zabochen.forecastapp.ui.weather.today.TodayWeatherFragment
import ua.ck.zabochen.forecastapp.ui.weather.week.WeekWeatherFragment

class MainViewModel : ViewModel() {

    var fragmentContainerState: MutableLiveData<Fragment> = MutableLiveData()
        private set

    fun selectedBottomNavigationItem(itemId: Int) {
        when (itemId) {
            R.id.menuBottomNavigation_itemTodayWeather -> fragmentContainerState.postValue(TodayWeatherFragment.newInstance())
            R.id.menuBottomNavigation_itemWeekWeather -> fragmentContainerState.postValue(WeekWeatherFragment.newInstance())
            R.id.menuBottomNavigation_itemSettings -> fragmentContainerState.postValue(SettingsFragment.newInstance())
        }
    }

}