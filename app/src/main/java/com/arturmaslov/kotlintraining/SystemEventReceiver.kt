package com.arturmaslov.kotlintraining

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

// static BroadcastReceiver
// dynamic in MainActivity.kt
class SystemEventReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        // This method will be called on the broadcast you are
        // listening for.
        // To avoid doing long running task here, you can choose
        // androidx.core.app.JobIntentService to do the task.
        // See the code of manifest to know about how to register
        // this receiver to listen to an event.
        if (intent.action?.equals(Intent.ACTION_BOOT_COMPLETED) == true) {
            // do smth
            Log.i("onReceive", "received");
            val intnt = Intent(context, ExampleService::class.java);
            context.startService(intnt);
        }
    }

}