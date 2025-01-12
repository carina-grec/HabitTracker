package com.example.habittracker

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class HabitDatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object {
        private const val DATABASE_NAME = "habit_database"
        private const val DATABASE_VERSION = 1
        const val TABLE_HABITS = "habits"
        const val COLUMN_ID = "id"
        const val COLUMN_NAME = "name"
        const val COLUMN_DESCRIPTION = "description"
        const val COLUMN_FREQUENCY = "frequency"
        const val COLUMN_COMPLETION_DATE = "completion_date"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTable = ("CREATE TABLE $TABLE_HABITS ($COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT, $COLUMN_NAME TEXT, $COLUMN_DESCRIPTION TEXT, $COLUMN_FREQUENCY TEXT, $COLUMN_COMPLETION_DATE TEXT)")
        db?.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_HABITS")
        onCreate(db)
    }

    class HabitRepository(context: Context) {
        private val dbHelper = HabitDatabaseHelper(context)

        fun addHabit(name: String, description: String, frequency: String) {
            val db = dbHelper.writableDatabase
            val values = ContentValues().apply {
                put(COLUMN_NAME, name)
                put(COLUMN_DESCRIPTION, description)
                put(COLUMN_FREQUENCY, frequency)
            }
            db.insert(TABLE_HABITS, null, values)
            db.close() // Close the database after the operation
        }
    }
}