package com.dog.sunshine

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.PreferenceManager
import com.dog.sunshine.ui.settings.Settings
import com.dog.sunshine.util.MEASUREMENT_UNIT
import com.dog.sunshine.util.METRIC

class MainActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.elevation = 0f

        setupPreferences()
    }

    private fun setupPreferences() {
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
        MEASUREMENT_UNIT = sharedPreferences.getString(
            resources.getString(R.string.type_degrees_key),
            METRIC
        )!!
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.action_settings -> {
                val intentSettings = Intent(this, Settings::class.java)
                startActivity(intentSettings)
                return true
            }
        }
        return true
    }
}