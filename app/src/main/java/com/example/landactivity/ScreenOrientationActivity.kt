package com.example.landactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Log.d
import android.widget.TextView
import java.util.logging.Logger

class ScreenOrientationActivity : AppCompatActivity() {
    val TAG  = this.javaClass.name
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val orientation = resources.configuration.orientation
        Log.e(TAG,"current screen is " + if (orientation == 1) "portrait" else " landscape ")
        setContentView(R.layout.activity_screen_orientation)
        val result = findViewById<TextView>(R.id.result)

    }
}