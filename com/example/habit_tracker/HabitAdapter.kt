package com.example.habittracker

import android.content.Context
import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.habit_tracker.Habit
import com.example.habit_tracker.MainActivity
import com.example.habit_tracker.R

class HabitAdapter(
    private val habitList: List<Habit>,
    private val context: MainActivity
) : RecyclerView.Adapter<HabitAdapter.HabitViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HabitViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_habit, parent, false)
        return HabitViewHolder(view)
    }

    override fun onBindViewHolder(holder: HabitViewHolder, position: Int) {
        val habit = habitList[position]
        holder.bind(habit)
    }

    override fun getItemCount(): Int {
        return habitList.size
    }

    inner class HabitViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val habitName: TextView = itemView.findViewById(R.id.habit_name)
        private val habitDescription: TextView = itemView.findViewById(R.id.habit_description)
        private val habitCheckBox: CheckBox = itemView.findViewById(R.id.habit_checkbox)

        fun bind(habit: Habit) {
            habitName.text = habit.name
            habitDescription.text = habit.description

            habitCheckBox.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    completeTask(habit)
                }
            }
        }
    }

    private fun completeTask(habit: Habit) {
        rewardTree()
        Toast.makeText(context, "Task '${habit.name}' completed!", Toast.LENGTH_SHORT).show()
    }

    private fun rewardTree() {
        val sharedPreferences: SharedPreferences =
            context.getSharedPreferences("TreeGardenPrefs", Context.MODE_PRIVATE)
        val currentTreeCount = sharedPreferences.getInt("treeCount", 0)

        // Increment the tree count
        val newTreeCount = currentTreeCount + 1
        sharedPreferences.edit().putInt("treeCount", newTreeCount).apply()

        // Notify the user
        Toast.makeText(context, "You earned a new tree! Check your garden.", Toast.LENGTH_SHORT).show()
    }
}
