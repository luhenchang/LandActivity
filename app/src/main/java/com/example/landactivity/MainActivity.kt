package com.example.landactivity

import android.content.DialogInterface.*
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import zui.app.MessageDialog
import zui.app.MessageDialog.TYPE_TEXTINPUT

class MainActivity : AppCompatActivity() {
    private val ACTION_SCIENCE = "com.example.landactivity.SCIENCE"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //val intent = Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION)
        //intent.data = Uri.parse("package:$packageName")
        //startActivityForResult(intent, 101) //GET_DIALOG_PERMISSION为预先定义好的返回结果常量

//        val isActionScience = ACTION_SCIENCE == intent.action
//        if (isActionScience) {
//            requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
//        }
    }


    private fun goToDialogShow() {
     val   labelDialog = MessageDialog.Builder(this, R.style.Theme_Zui_MessageDialog).create()
        labelDialog.setMessageDialogType(TYPE_TEXTINPUT)
        labelDialog.setTitle("备注")
        labelDialog.editorText = ""
        labelDialog.setEditorHint("请输入")
        labelDialog.setEditorTextWatcher(mTextWatcher)
        labelDialog.window!!.setSoftInputMode(
            WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE
        )
        labelDialog.setCanceledOnTouchOutside(false)
        labelDialog.setButton(
            BUTTON_POSITIVE,
            "确定"
        ) { dialog, which ->

        }
        labelDialog.setButton(
            BUTTON_NEGATIVE,
            "关闭",
        ){ dialog, which ->

        }
        labelDialog
            .setOnDismissListener {

            }
        labelDialog.show()
        labelDialog.editor.apply {
            this.requestFocus()
        }
    }
    private var mTextWatcher: TextWatcher = object : TextWatcher {
        override fun onTextChanged(
            s: CharSequence, start: Int, before: Int,
            count: Int
        ) {
            // TODO Auto-generated method stub
        }

        override fun beforeTextChanged(
            s: CharSequence, start: Int, count: Int,
            after: Int
        ) {
            // TODO Auto-generated method stub
            // mTextView.setText(s);//将输入的内容实时显示
        }

        override fun afterTextChanged(s: Editable) {
            // TODO Auto-generated method stub
            val content = s.toString()
            if (s.isNotEmpty()) {

            }

            // 当title超过最低30个字节才会去判断是否有中文和英文字符
            if (s.length > 13) {
                var deleteStartPosition = 0
                var dstLength = 0
                val titleChars = content.toCharArray()
                for (i in titleChars.indices) {
                    dstLength++
                    if (Char(titleChars[i].code.toByte().toUShort()) != titleChars[i]) {
                        dstLength++
                    }
                    if (dstLength >= 27) {
                        deleteStartPosition = if (Character.isHighSurrogate(s[i - 1]) &&
                            Character.isLowSurrogate(s[i])
                        ) {
                            i - 1
                        } else {
                            i
                        }
                        break
                    }
                }
                if (deleteStartPosition > 0) {
                    s.delete(deleteStartPosition, s.length)
                }
            }
        }
    }
    fun goToScreenActivity(view: View) {
        goToDialogShow()
//        val orientation = resources.configuration.orientation
//
//        requestedOrientation = if (orientation == 1) {
//            ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
//
//        } else {
//            ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
//
//        }
        //startActivityForResult(Intent(this,ScreenOrientationActivity::class.java),100)
    }

    private fun createIntent(action: String?): Intent {
        return Intent().setAction(action).putExtra(TimerConstants.TIMER_INTENT_EXTRA, "111")
    }

}