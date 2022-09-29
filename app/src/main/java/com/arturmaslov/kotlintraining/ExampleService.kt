package com.arturmaslov.kotlintraining

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log

// even if it runs into the background of the application, it runs on the application’s
// main thread (can cause Application Not Responding error (ANR))
// common uses:
// Audio player application
// Long-running task without UI
// Access location information
// Client-Server interface between applications. Or Inter-Process Communication (IPC)
class ExampleService : Service() {

    override fun onCreate() {
        // Can start a new thread which can perform long running
        // task on background Thread (can use HandlerThread).
    }

    override fun onStartCommand(
        intent: Intent,
        flags: Int,
        startId: Int
    ): Int {
        // Pass this intent to thread which we started on onCreate(),
        // so that can be completed by that thread on background thread.
        Log.i("ExampleService", "onStartCommand");
        // Restart, if we get killed.
        return START_STICKY
    }

    override fun onBind(intent: Intent): IBinder? {
        // When we want to expose some functionality to other
        // application through Interprocess communication (IPC),
        // we use bound services. Here in example, we are not
        // using bound service so we are returning null.
        return null
    }
}