package com.example.landactivity

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.widget.CompoundButton
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import zui.app.MessageDialog

class MainActivity : AppCompatActivity() {
    private val ACTION_SCIENCE = "com.example.landactivity.SCIENCE"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        val isActionScience = ACTION_SCIENCE == intent.action
//        if (isActionScience) {
//            requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
//        }
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        //showPowerDialogIfNeeded()
    }
    //需要获取访问本地录音权限的dialog
    private fun showPowerDialogIfNeeded() {
        val checkedChangeListener =
            CompoundButton.OnCheckedChangeListener { buttonView, isChecked ->
                {

                }
            }
        val listener =
            DialogInterface.OnClickListener { dialog, which ->
                when (which) {
                    DialogInterface.BUTTON_POSITIVE -> {

                    }
                    DialogInterface.BUTTON_NEGATIVE -> {
                    }
                }
            }
        val builder: MessageDialog.Builder = MessageDialog.Builder(this)
            .setTitle("录音机权限")
            .setMessageDialogType(MessageDialog.TYPE_PERMISSION)
            .setCheckMessage("再次统一么？", true)
            .setOnCheckedChangeListener(checkedChangeListener)
            .addGroup("网络权限统一福", 0)
            .addPairedItem2Group(
                0,
                "访问网络",
                "访问日历信息",
                AppCompatResources.getDrawable(this, R.drawable.ic_launcher_foreground)
            ).addPairedItem2Group(
                0,
                "访问日历",
                "访问日历信息",
                AppCompatResources.getDrawable(this, R.drawable.ic_launcher_foreground)
            )
            .addPairedItem2Group(
                0,
                "访问储存设备",
                "读取内测中的设备日历相关日志",
                AppCompatResources.getDrawable(this, R.drawable.ic_launcher_foreground)
            ).setPositiveButton("同意", listener)
            .setNegativeButton("取消", listener)
//        val titles = resources.getStringArray(R.array.cta_items_title)
//        val msgs = resources.getStringArray(R.array.cta_items_msg)
//        for (i in titles.indices) {
//            builder.addPairedItem2Group(
//                0,
//                titles[i],
//                msgs[i],
//                getDrawable(R.drawable.ic_launcher_foreground)
//            )
//        }
        val dialog: MessageDialog = builder.create()
        dialog.setCancelableOnOrientation(false)
        dialog.setOnKeyListener { _, keyCode, event -> keyCode === KeyEvent.KEYCODE_BACK }
        dialog.show()
    }

    fun goToScreenActivity(view: View) {
        val intent: Intent? = createIntent(TimerConstants.TIMES_UP)?.setClass(
            this,
            TimerReceiver::class.java
        )
        sendBroadcast(intent)
        //requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
    }
    private fun createIntent(action: String?): Intent {
        return Intent().setAction(action).putExtra(TimerConstants.TIMER_INTENT_EXTRA,"111")
    }

}