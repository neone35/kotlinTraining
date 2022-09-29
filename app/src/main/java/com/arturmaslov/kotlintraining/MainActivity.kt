package com.arturmaslov.kotlintraining

import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

// TAG for logs of MainActivity, so filtering of logs for
// this activity by TAG would be easy.
private val TAG = MainActivity::class.java.simpleName

// AppCompatActivity is the type of Activity, which can be used to use newer platform features on older Android versions/devices
class MainActivity : AppCompatActivity() {

    var systemEventReceiver: SystemEventReceiver? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        writeLogs();
        eatMemory();
        startMyService(applicationContext);
        dynamicBroadcastReceiver();
        addFragment(BlankFragment.newInstance("first", "second"));

    }

    private fun writeLogs() {
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

    private fun eatMemory() {
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

    private fun startMyService(ctx: Context) {
        // starting service
        // Since Android API 26 better to use WorkManager and Coroutines
        val intent = Intent(ctx, ExampleService::class.java);
        ctx.startService(intent);
        // same
        Intent(ctx, ExampleService::class.java).also { myIntent ->
            startService(myIntent)
        }
    }

    private fun dynamicBroadcastReceiver() {
        val filter = IntentFilter(Intent.ACTION_SCREEN_ON)
        filter.addAction(Intent.ACTION_SCREEN_OFF)
        val systemEventReceiver = SystemEventReceiver()
        registerReceiver(systemEventReceiver, filter);
    }

    private fun addFragment(frag: Fragment) {
        val fragmentManager: FragmentManager = supportFragmentManager
        val ft: FragmentTransaction = fragmentManager.beginTransaction()
        // Can be used replace() or add() to show fragment with either
        // using addToBackStack() or without addToBackStack()
        // addToBackStack() adds fragment transactions to the back stack, so when
        // you press back navigation, you will return to the previous Fragment
        ft.add(R.id.container, frag).addToBackStack(null).commit()
        // replace() first removes previously added views from the container and then adds the Fragment
        // ft.replace(R.id.container, fragment).addToBackStack(null).commit()
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(systemEventReceiver)
    }
}


