package com.example.habittracker.data

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.habittracker.R

class HabitAdapter(private val habits: List<Habit>, private val onHabitClick: (Habit) -> Unit) :
    RecyclerView.Adapter<HabitAdapter.HabitViewHolder>() {

    class HabitViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val habitName: TextView = itemView.findViewById(R.id.habit_name)
        val habitDescription: TextView = itemView.findViewById(R.id.habit_description)
        val habitCheckBox: CheckBox = itemView.findViewById(R.id.habit_checkbox)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HabitViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_habit, parent, false)
        return HabitViewHolder(view)
    }

    override fun onBindViewHolder(holder: HabitViewHolder, position: Int) {
        val habit = habits[position]
        holder.habitName.text = habit.name
        holder.habitDescription.text = habit.description
        holder.habitCheckBox.isChecked = habit.completed

        holder.itemView.setOnClickListener {
            onHabitClick(habit)
        }
    }

    override fun getItemCount(): Int = habits.size
}