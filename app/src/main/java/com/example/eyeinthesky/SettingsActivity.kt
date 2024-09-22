package com.example.eyeinthesky
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.switchmaterial.SwitchMaterial

class SettingsActivity : AppCompatActivity() {
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        sharedPreferences = getSharedPreferences("UserSettings", Context.MODE_PRIVATE)

        val toggleMetricSystem = findViewById<SwitchMaterial>(R.id.toggleMetricSystem)
        val maxDistance = findViewById<EditText>(R.id.maxDistance)

        toggleMetricSystem.isChecked = sharedPreferences.getBoolean("metric", true)
        maxDistance.setText(sharedPreferences.getInt("maxDistance", 50).toString())

        toggleMetricSystem.setOnCheckedChangeListener { _, isChecked ->
            sharedPreferences.edit().putBoolean("metric", isChecked).apply()
        }

        maxDistance.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                sharedPreferences.edit().putInt("maxDistance", maxDistance.text.toString().toInt()).apply()
            }
        }
    }
}
