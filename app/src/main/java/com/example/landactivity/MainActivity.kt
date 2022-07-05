package com.example.landactivity

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.widget.CompoundButton
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import zui.app.MessageDialog

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        showPowerDialogIfNeeded()
    }



    //需要获取访问本地录音权限的dialog
    private fun showPowerDialogIfNeeded() {
        val checkedChangeListener =
            CompoundButton.OnCheckedChangeListener { buttonView, isChecked -> {

            }}
        val listener =
            DialogInterface.OnClickListener { dialog, which ->
                when (which) {
                    DialogInterface.BUTTON_POSITIVE -> {

                    }
                    DialogInterface.BUTTON_NEGATIVE -> {
                        finish()
                    }
                }
            }
        val builder: MessageDialog.Builder = MessageDialog.Builder(this)
            .setTitle("录音机权限")
            .setMessageDialogType(MessageDialog.TYPE_PERMISSION)
            .setCheckMessage("再次统一么？", true)
            .setOnCheckedChangeListener(checkedChangeListener)
            .addGroup("网络权限统一福", 0)
            .setPositiveButton("同意", listener)
            .setNegativeButton("取消", listener)
        val titles = resources.getStringArray(R.array.cta_items_title)
        val msgs = resources.getStringArray(R.array.cta_items_msg)
        for (i in titles.indices) {
            builder.addPairedItem2Group(
                0,
                titles[i],
                msgs[i],
                getDrawable(R.drawable.ic_launcher_foreground)
            )
        }
        val dialog: MessageDialog = builder.create()
        dialog.setCancelableOnOrientation(false)
        dialog.setOnKeyListener { _, keyCode, event -> keyCode === KeyEvent.KEYCODE_BACK }
        dialog.show()
    }
}