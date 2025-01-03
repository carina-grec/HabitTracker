package com.example.habittracker

import android.app.Service
import android.content.Intent
import android.os.Handler
import android.os.IBinder
import android.util.Log

class MyBackgroundService : Service() {

    private val handler = Handler()
    private val runnable = object : Runnable {
        override fun run() {
            // Perform background task here (e.g., sync data)
            Log.d("MyBackgroundService", "Background task running")
            handler.postDelayed(this, 60000) // Repeat every minute
        }
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        handler.post(runnable)
        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacks(runnable)
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }
}