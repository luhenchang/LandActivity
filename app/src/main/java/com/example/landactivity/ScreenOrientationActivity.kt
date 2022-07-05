package com.example.landactivity

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ScreenOrientationActivity : AppCompatActivity() {
    val TAG  = this.javaClass.name
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e(TAG, "onCreate:::")
        val win = window
        win.addFlags(
            WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED
                    or WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON
                    or WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
                    or WindowManager.LayoutParams.FLAG_ALLOW_LOCK_WHILE_SCREEN_ON
        )
        setContentView(R.layout.activity_screen_orientation)
        val result = findViewById<TextView>(R.id.result)
        val orientation = resources.configuration.orientation
        Log.e(TAG, "current screen orientation = $orientation")
        Log.e(TAG, "current screen is " + if (orientation == 1) "portrait" else " landscape ")
        val currentOrientation =
            if (orientation == 1) ActivityInfo.SCREEN_ORIENTATION_PORTRAIT else ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        requestedOrientation = currentOrientation

    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e(TAG, "onDestroy:::")
    }
}