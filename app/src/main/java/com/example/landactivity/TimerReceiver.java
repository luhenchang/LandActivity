/*
 * Copyright (C) 2012 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.landactivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;


public class TimerReceiver extends BroadcastReceiver {
    private static final String TAG = "TimerReceiver";

    @Override
    public void onReceive(final Context context, final Intent intent) {
        Log.d(TAG, "onReceive :: intent = " + intent);

        String action = intent.getAction();
        switch (action) {
            case TimerConstants.NOTIF_IN_USE_SHOW:
                break;
            case TimerConstants.NOTIF_AUTO_STOP_RINGING:
                break;
            case TimerConstants.TIMES_UP:
                Toast.makeText(context, "TIMES_UP", Toast.LENGTH_SHORT).show();
                Log.e("TIME_UP","TIMES_UP");
                break;
            case TimerConstants.NOTIF_TIMES_UP_RESET:
                break;
            case Intent.ACTION_LOCALE_CHANGED:
                break;
            default:
                break;
        }
    }
}
