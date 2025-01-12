package com.example.habit_tracker

data class Habit(
    val id: Int,
    val name: String,
    val description: String,
    val frequency: String,
    val completionDate: String,
    val completed: Boolean
)