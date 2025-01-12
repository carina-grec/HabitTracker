package com.example.habit_tracker

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.ToggleButton
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.habittracker.HabitAdapter

class MainActivity : AppCompatActivity() {
    private lateinit var habitRepo: HabitRepo
    private lateinit var habitAdapter: HabitAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var themePreferenceText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE)

        // Get the saved theme preference
        val isDarkMode = sharedPreferences.getBoolean("isDarkMode", false)

        setAppTheme(isDarkMode)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        themePreferenceText = findViewById(R.id.theme_preference_text)
        updateThemePreferenceText(isDarkMode)

        habitRepo = HabitRepo(this)

        recyclerView = findViewById(R.id.habit_list)
        recyclerView.layoutManager = LinearLayoutManager(this)

        loadHabits()

        val addHabitButton: Button = findViewById(R.id.add_habit_button)
        addHabitButton.setOnClickListener {
            val intent = Intent(this, AddHabitActivity::class.java)
            startActivity(intent)
        }

        val themeSwitch: ToggleButton = findViewById(R.id.theme_toggle_button)
        themeSwitch.isChecked = isDarkMode
        themeSwitch.setOnCheckedChangeListener { _, isChecked ->
            sharedPreferences.edit().putBoolean("isDarkMode", isChecked).apply()
            setAppTheme(isChecked)
            recreate()
        }

        val startServiceButton: Button = findViewById(R.id.start_service_button)
        startServiceButton.setOnClickListener {
            val serviceIntent = Intent(this, MyForegroundService::class.java)
            startService(serviceIntent)
        }

        val stopServiceButton: Button = findViewById(R.id.stop_service_button)
        stopServiceButton.setOnClickListener {
            val serviceIntent = Intent(this, MyForegroundService::class.java)
            stopService(serviceIntent)
        }

        val startBackgroundServiceButton: Button = findViewById(R.id.start_background_service_button)
        startBackgroundServiceButton.setOnClickListener {
            val backgroundServiceIntent = Intent(this, MyBackgroundService::class.java)
            startService(backgroundServiceIntent)
        }

        val viewGardenButton: Button = findViewById(R.id.view_garden_button)
        viewGardenButton.setOnClickListener {
            val intent = Intent(this, GardenActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setAppTheme(isDarkMode: Boolean) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)

        if (isDarkMode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
    }

    private fun updateThemePreferenceText(isDarkMode: Boolean) {
        val themeText = if (isDarkMode) "Current Theme: Dark" else "Current Theme: Light"
        themePreferenceText.text = themeText
    }

    private fun loadHabits() {
        val habits = habitRepo.getHabits()
        habitAdapter = HabitAdapter(habits, this)
        recyclerView.adapter = habitAdapter
    }
}
