package com.dog.sunshine.ui.settings

import android.app.Activity
import android.content.SharedPreferences
import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceManager
import com.dog.sunshine.R
import com.dog.sunshine.ui.weather.WeatherFragment
import com.dog.sunshine.util.MEASUREMENT_UNIT
import com.dog.sunshine.util.METRIC

class SettingsFragment : PreferenceFragmentCompat(), SharedPreferences.OnSharedPreferenceChangeListener {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)
    }

    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences, key: String?) {
        if(key.equals(getString(R.string.type_degrees_key))){
            MEASUREMENT_UNIT = sharedPreferences.getString(
                key,
                METRIC
            )!!
        }
    }

    override fun onStart() {
        super.onStart()
        PreferenceManager.getDefaultSharedPreferences(
            requireContext()
        ).registerOnSharedPreferenceChangeListener(this)
    }

    override fun onStop() {
        super.onStop()
        PreferenceManager.getDefaultSharedPreferences(
            requireContext()
        ).unregisterOnSharedPreferenceChangeListener(this)
    }
}