package ua.ck.zabochen.forecastapp.ui.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ua.ck.zabochen.forecastapp.R

class SettingsFragment : Fragment() {

    companion object {
        fun newInstance(): SettingsFragment {
            val bundle = Bundle()
            bundle.putString("tag", "SettingsFragment")
            return SettingsFragment().also { it.arguments = bundle }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }

}