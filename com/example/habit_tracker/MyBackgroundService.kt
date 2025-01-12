package com.example.habit_tracker

import android.app.IntentService
import android.content.Intent
import android.util.Log

class MyBackgroundService : IntentService("MyBackgroundService") {

    override fun onHandleIntent(intent: Intent?) {
        // Simulate a long-running background task
        for (i in 1..5) {
            Log.d("MyBackgroundService", "Background task running... $i")
            Thread.sleep(1000) // Simulate work by sleeping for 1 second
        }
        Log.d("MyBackgroundService", "Background task completed")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("MyBackgroundService", "Background Service stopped")
    }
}
