package com.example.landactivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private lateinit var helloView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val orientation = resources.configuration.orientation
        Log.e(
            "MainActivity",
            "current screen is " + if (orientation == 1) "portrait" else " landscape "
        )
        setContentView(R.layout.activity_screen_orientation)
        setContentView(R.layout.activity_main)
        helloView = findViewById<TextView>(R.id.helloId)
    }
    
    fun goToScreenActivity(view: View) {
        helloView.postDelayed({
            val fullScreenIntent = Intent(this, ScreenOrientationActivity::class.java)
            fullScreenIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(fullScreenIntent)
        }, 4000)

    }
}