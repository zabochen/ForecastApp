package ua.ck.zabochen.forecastapp.ui.weather.today

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.Group
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.Unbinder
import kotlinx.coroutines.launch
import org.jetbrains.anko.AnkoLogger
import ua.ck.zabochen.forecastapp.MainApp
import ua.ck.zabochen.forecastapp.R
import ua.ck.zabochen.forecastapp.internal.glide.GlideApp
import ua.ck.zabochen.forecastapp.ui.base.BaseFragment
import javax.inject.Inject

class TodayWeatherFragment : BaseFragment(), AnkoLogger {

    companion object {
        fun newInstance(): TodayWeatherFragment {
            val bundle = Bundle()
            bundle.putString("tag", "TodayWeatherFragment")
            return TodayWeatherFragment().also { it.arguments = bundle }
        }
    }

    @Inject
    lateinit var todayWeatherViewModelFactory: TodayWeatherViewModelFactory

    private lateinit var todayWeatherViewModel: TodayWeatherViewModel

    private lateinit var unbinder: Unbinder

    @BindView(R.id.fragmentTodayWeather_group_loading)
    lateinit var groupLoading: Group

    @BindView(R.id.fragmentTodayWeather_group_content)
    lateinit var groupContent: Group

    @BindView(R.id.fragmentTodayWeather_textView_condition)
    lateinit var condition: TextView

    @BindView(R.id.fragmentTodayWeather_textView_temperature)
    lateinit var temperature: TextView

    @BindView(R.id.fragmentTodayWeather_imageView_conditionIcon)
    lateinit var conditionIcon: ImageView

    @BindView(R.id.fragmentTodayWeather_textView_temperatureFeelsLike)
    lateinit var temperatureFeelsLike: TextView

    @BindView(R.id.fragmentTodayWeather_textView_windSpeed)
    lateinit var windSpeed: TextView

    override fun onAttach(context: Context) {
        super.onAttach(context)

        // Dagger
        MainApp.appComponent().inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_today_weather, container, false)
        this.unbinder = ButterKnife.bind(this, view)
        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Attach ViewModel
        this.todayWeatherViewModel = ViewModelProviders.of(this, todayWeatherViewModelFactory)
            .get(TodayWeatherViewModel::class.java)

        setUi()
    }

    private fun setUi() = launch {
        val todayWeather = todayWeatherViewModel.todayWeather.await()
        todayWeather.observe(this@TodayWeatherFragment, Observer {
            if (it == null) return@Observer

            // Toolbar
            updateLocation("Cherkasy")
            updateDateToToday()

            // Layout
            updateCondition(it.conditionText)
            updateTemperatures(it.temperature, it.feelsLikeTemperature)
            updateWindSpeed(it.windSpeed)

            // Condition Icon
            GlideApp.with(this@TodayWeatherFragment)
                .load("http:${it.conditionIconUrl}")
                .into(conditionIcon)

            // Hide GroupLoading and show GroupContent
            groupLoading.visibility = View.GONE
            groupContent.visibility = View.VISIBLE
        })
    }

    private fun selectUnitAbbreviation(metric: String, imperial: String): String {
        return if (todayWeatherViewModel.isMetric) metric else imperial
    }

    private fun updateLocation(location: String) {
        (activity as? AppCompatActivity)?.supportActionBar?.title = location
    }

    private fun updateDateToToday() {
        (activity as? AppCompatActivity)?.supportActionBar?.subtitle = "Today"
    }

    private fun updateTemperatures(temperature: Double, feelsLike: Double) {
        val unitAbbreviation: String = selectUnitAbbreviation(metric = "°C", imperial = "°F")
        this.temperature.text = "$temperature$unitAbbreviation"
        this.temperatureFeelsLike.text = "Feels like $feelsLike$unitAbbreviation"
    }

    private fun updateCondition(condition: String) {
        this.condition.text = condition
    }

    private fun updateWindSpeed(windSpeed: Double) {
        val unitAbbreviation = selectUnitAbbreviation(metric = "kph", imperial = "mph")
        this.windSpeed.text = "Wind speed: $windSpeed $unitAbbreviation"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        if (::unbinder.isInitialized) unbinder.unbind()
    }
}