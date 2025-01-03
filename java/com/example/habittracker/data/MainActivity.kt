package com.example.habittracker.data

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.habittracker.R
import com.example.habittracker.MyBackgroundService

class MainActivity : ComponentActivity() {
    private lateinit var habitRepo: HabitRepo
    private lateinit var habitAdapter: HabitAdapter
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        habitRepo = HabitRepo(this)
        recyclerView = findViewById(R.id.habit_list)
        recyclerView.layoutManager = LinearLayoutManager(this)

        loadHabits()

        // Start the background service
        val serviceIntent = Intent(this, MyBackgroundService::class.java)
        startService(serviceIntent)

        val addHabitButton: Button = findViewById(R.id.add_habit_button)
        addHabitButton.setOnClickListener {
            // Start AddHabitActivity when the button is clicked
            val intent = Intent(this, AddHabitActivity::class.java)
            startActivity(intent)
        }
    }

    private fun loadHabits() {
        val habits = habitRepo.getHabits()
        habitAdapter = HabitAdapter(habits) { habit ->
            // Handle habit click
            toggleHabitCompletion(habit)
        }
        recyclerView.adapter = habitAdapter
    }

    private fun toggleHabitCompletion(habit: Habit) {
        // Update the completion status of the habit
        val updatedHabit = habit.copy(completed = !habit.completed)
        habitRepo.updateHabit(updatedHabit) // You need to implement this method in HabitRepository
        loadHabits() // Reload habits to refresh the RecyclerView
    }
}