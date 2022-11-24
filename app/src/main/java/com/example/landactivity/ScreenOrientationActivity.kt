package com.example.landactivity

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.view.WindowManager
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import java.lang.reflect.InvocationTargetException

class ScreenOrientationActivity : AppCompatActivity() {
    val TAG = this.javaClass.name
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e(TAG, "onCreate:::")
        trueOnLight()
        setContentView(R.layout.activity_screen_orientation)
        val result = findViewById<TextView>(R.id.result)
        val orientation = resources.configuration.orientation
        Log.e(TAG, "current screen orientation = $orientation")
        Log.e(TAG, "current screen is " + if (orientation == 1) "portrait" else " landscape ")
        val currentOrientation =
            if (orientation == 1) ActivityInfo.SCREEN_ORIENTATION_PORTRAIT else ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        requestedOrientation = currentOrientation

    }

    @SuppressLint("SoonBlockedPrivateApi")
    private fun trueOnLight() {
        val win = window
        win.addFlags(
            WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED
                    or WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON
                    or WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
                    or WindowManager.LayoutParams.FLAG_ALLOW_LOCK_WHILE_SCREEN_ON
        )
        setFullScreenOnP(win)
        try {
            val addPrivateFlagsMethod = Class.forName("android.view.Window").getDeclaredMethod(
                "addPrivateFlags",
                Int::class.javaPrimitiveType
            )
            //WindowManager.LayoutParams.PRIVATE_FLAG_SHOW_WHEN_LOCKED_FOR_HALL_NEAR = 0x40000000
            addPrivateFlagsMethod.invoke(win, 0x40000000)
        } catch (e: NoSuchMethodException) {
            Log.e(TAG, e.toString())
        } catch (e: InvocationTargetException) {
            Log.e(TAG, e.toString())
        } catch (e: ClassNotFoundException) {
            Log.e(TAG, e.toString())
        } catch (e: IllegalAccessException) {
            Log.e(TAG, e.toString())
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e(TAG, "onDestroy:::")
    }

    fun setFullScreenOnP(window: Window) {
        val layoutParams = window.attributes
        layoutParams.layoutInDisplayCutoutMode =
            WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES
        window.attributes = layoutParams
    }
}