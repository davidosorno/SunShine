package com.dog.sunshine.ui.settings

import android.content.SharedPreferences
import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceManager
import com.dog.sunshine.R
import com.dog.sunshine.util.MEASUREMENT_UNITS

class SettingsFragment : PreferenceFragmentCompat(), SharedPreferences.OnSharedPreferenceChangeListener {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)
        setupSharePreferences()
    }

    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences, key: String?) {
        if(key.equals(getString(R.string.type_degrees_key))){
            MEASUREMENT_UNITS = sharedPreferences.getString(key, "F")!!

        }
    }

    private fun setupSharePreferences() {
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
        MEASUREMENT_UNITS = sharedPreferences.getString(
            resources.getString(R.string.type_degrees_key)
            , "F"
        )!!
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