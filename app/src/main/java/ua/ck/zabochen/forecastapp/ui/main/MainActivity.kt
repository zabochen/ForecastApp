package ua.ck.zabochen.forecastapp.ui.main

import android.os.Bundle
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.Unbinder
import com.google.android.material.bottomnavigation.BottomNavigationView
import ua.ck.zabochen.forecastapp.R
import ua.ck.zabochen.forecastapp.ui.weather.today.TodayWeatherFragment

private const val TAG: String = "MainActivity"

class MainActivity : AppCompatActivity() {

    private lateinit var mainViewModel: MainViewModel

    private lateinit var unbinder: Unbinder

    @BindView(R.id.widget_toolbar)
    lateinit var toolbar: Toolbar

    @BindView(R.id.activityMain_frameLayout_fragmentContainer)
    lateinit var fragmentContainer: FrameLayout

    @BindView(R.id.activityMain_bottomNavigation)
    lateinit var bottomNavigation: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUi()
        setViewModelSubscription()

        // Set default fragment
        if (savedInstanceState == null) setFragment(
            TodayWeatherFragment.newInstance(),
            TodayWeatherFragment.newInstance().arguments?.getString("tag")
        )
    }

    override fun onDestroy() {
        // Clear resources
        if (::unbinder.isInitialized) unbinder.unbind()
        super.onDestroy()
    }

    private fun setUi() {
        // Layout & ButterKnife
        setContentView(R.layout.activity_main)
        this.unbinder = ButterKnife.bind(this)

        // ViewModel
        this.mainViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        // Toolbar
        setSupportActionBar(toolbar)

        // Bottom Navigation
        this.bottomNavigation.setOnNavigationItemSelectedListener { menuItem ->
            mainViewModel.selectedBottomNavigationItem(menuItem.itemId)
            true
        }
    }

    private fun setViewModelSubscription() {
        // Replace fragments
        this.mainViewModel.fragmentContainerState.observe(this, Observer<Fragment> { fragment ->
            setFragment(fragment, fragment.arguments?.getString("tag"))
        })
    }

    private fun setFragment(fragment: Fragment, tag: String?) {
        supportFragmentManager.findFragmentByTag(tag).also {
            it ?: supportFragmentManager.beginTransaction()
                .replace(fragmentContainer.id, fragment, tag)
                .commit()
        }
    }
}
