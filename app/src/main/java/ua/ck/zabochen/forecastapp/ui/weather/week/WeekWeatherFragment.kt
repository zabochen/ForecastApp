package ua.ck.zabochen.forecastapp.ui.weather.week

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ua.ck.zabochen.forecastapp.R

class WeekWeatherFragment : Fragment() {

    companion object {
        fun newInstance(): WeekWeatherFragment {
            val bundle = Bundle()
            bundle.putString("tag", "WeekWeatherFragment")
            return WeekWeatherFragment().also { it.arguments = bundle }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_week_weather, container, false)
    }

}