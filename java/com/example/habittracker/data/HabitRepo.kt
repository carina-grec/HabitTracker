package com.example.habittracker.data

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase

class HabitRepo(context: Context) {
    private val dbHelper: HabitDatabaseHelper = HabitDatabaseHelper(context)

    fun addHabit(name: String, description: String, frequency: String, completionDate: String) {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put(HabitDatabaseHelper.COLUMN_NAME, name)
            put(HabitDatabaseHelper.COLUMN_DESCRIPTION, description)
            put(HabitDatabaseHelper.COLUMN_FREQUENCY, frequency)
            put(HabitDatabaseHelper.COLUMN_COMPLETION_DATE, completionDate)
        }
        db.insert(HabitDatabaseHelper.TABLE_HABITS, null, values)
    }

    fun getHabits(): List<Habit> {
        val habits = mutableListOf<Habit>()
        val db = dbHelper.readableDatabase
        val cursor: Cursor = db.query(HabitDatabaseHelper.TABLE_HABITS, null, null, null, null, null, null)
        with(cursor) {
            while (moveToNext()) {
                val id = getInt(getColumnIndexOrThrow(HabitDatabaseHelper.COLUMN_ID))
                val name = getString(getColumnIndexOrThrow(HabitDatabaseHelper.COLUMN_NAME))
                val description = getString(getColumnIndexOrThrow(HabitDatabaseHelper.COLUMN_DESCRIPTION))
                val frequency = getString(getColumnIndexOrThrow(HabitDatabaseHelper.COLUMN_FREQUENCY))
                val completionDate = getString(getColumnIndexOrThrow(HabitDatabaseHelper.COLUMN_COMPLETION_DATE))
                habits.add(Habit(id, name, description, frequency, completionDate, false))
            }
        }
        cursor.close()
        db.close()
        return habits
    }

    fun updateHabit(habit: Habit) {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put(HabitDatabaseHelper.COLUMN_NAME, habit.name)
            put(HabitDatabaseHelper.COLUMN_DESCRIPTION, habit.description)
            put(HabitDatabaseHelper.COLUMN_FREQUENCY, habit.frequency)
            put(HabitDatabaseHelper.COLUMN_COMPLETION_DATE, habit.completionDate)
        }
        db.update(HabitDatabaseHelper.TABLE_HABITS, values, "${HabitDatabaseHelper.COLUMN_ID} = ?", arrayOf(habit.id.toString()))
        db.close()
    }
}