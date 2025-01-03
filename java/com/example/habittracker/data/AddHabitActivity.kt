package com.example.habittracker.data

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.ComponentActivity
import com.example.habittracker.R

class AddHabitActivity : ComponentActivity() {
    private lateinit var habitRepo: HabitRepo

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_habit)

        habitRepo = HabitRepo(this)

        val nameEditText: EditText = findViewById(R.id.habit_name_edit_text)
        val descriptionEditText: EditText = findViewById(R.id.habit_description_edit_text)
        val frequencyEditText: EditText = findViewById(R.id.habit_frequency_edit_text)
        val completionDateEditText: EditText = findViewById(R.id.habit_completion_date_edit_text)
        val saveButton: Button = findViewById(R.id.save_habit_button)

        saveButton.setOnClickListener {
            val name = nameEditText.text.toString()
            val description = descriptionEditText.text.toString()
            val frequency = frequencyEditText.text.toString()
            val completionDate = completionDateEditText.text.toString()

            if (name.isNotEmpty() && description.isNotEmpty() && frequency.isNotEmpty() && completionDate.isNotEmpty()) {
                habitRepo.addHabit(name, description, frequency, completionDate)

                // Send a notification after adding a habit
                val notificationHelper = NotificationHelper(this)
                notificationHelper.sendNotification("Habit Added", "You have successfully added a new habit: $name")

                finish() // Close the activity and return to MainActivity
            }
        }
    }
}