package com.arturmaslov.kotlintraining

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

// TAG for logs of MainActivity, so filtering of logs for
// this activity by TAG would be easy.
private val TAG = MainActivity::class.java.simpleName

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        testLog();
        eatMemory();

    }
}

fun testLog() {
    // Write a debug log
    Log.d(TAG, "Hello world!!!")
    // Similarly you can use other methods of this class to show different type of messages. Like
    Log.e(
        TAG, "An error log, which you can put into a block which" +
                " you see it as an error . Like catch blocks of try -catch"
    )
    Log.i(TAG, "Informative messages, like Login Success")
    Log.w(
        TAG, "Use this method when seeing something which"
                + "might cause an error, "
                + "like some unexpected arguments to a method"
    )
}
fun eatMemory() {
    // This code block has been added to show you an example for memory profiler
    Thread {
        for (i in 0..20000) {
            Log.d(
                TAG, MemoryEater(stringVal = "temp")
                    .toString()
            )
        }
    }.start()
}
