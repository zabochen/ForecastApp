package ua.ck.zabochen.forecastapp.ui.weather.today

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.Unbinder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import ua.ck.zabochen.forecastapp.R
import ua.ck.zabochen.forecastapp.data.network.WeatherNetworkImpl
import ua.ck.zabochen.forecastapp.data.network.interceptor.connection.ConnectionStateInterceptorImpl
import ua.ck.zabochen.forecastapp.data.network.service.ApixuWeatherApiService

class TodayWeatherFragment : Fragment() {

    companion object {
        fun newInstance() = TodayWeatherFragment()
    }

    private lateinit var todayWeatherViewModel: TodayWeatherViewModel

    private lateinit var unbinder: Unbinder

    @BindView(R.id.fragmentTodayWeather_textView_todayWeather)
    lateinit var todayWeather: TextView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_today_weather, container, false)
        this.unbinder = ButterKnife.bind(this, view)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        // Attach ViewModel
        this.todayWeatherViewModel = ViewModelProviders.of(this).get(TodayWeatherViewModel::class.java)

        // Network Request
        val apixuWeatherApiService = ApixuWeatherApiService(ConnectionStateInterceptorImpl(context!!))
        val weatherNetwork = WeatherNetworkImpl(apixuWeatherApiService)
        weatherNetwork.downloadedTodayWeather.observe(this, Observer {
            this@TodayWeatherFragment.todayWeather.text = it.toString()
        })

        GlobalScope.launch(Dispatchers.Main) {
            weatherNetwork.fetchTodayWeather("Cherkasy", "uk")
        }
    }
}