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
import com.arturmaslov.kotlintraining.archpatterns.*
import com.arturmaslov.kotlintraining.archpatterns.solid.*
import com.arturmaslov.kotlintraining.components.BlankFragment
import com.arturmaslov.kotlintraining.components.ExampleService
import com.arturmaslov.kotlintraining.components.SystemEventReceiver

// TAG for logs of MainActivity, so filtering of logs for
// this activity by TAG would be easy.
private val TAG = MainActivity::class.java.simpleName

// AppCompatActivity is the type of Activity, which can be used to use newer platform features on older Android versions/devices
class MainActivity : AppCompatActivity() {

    var systemEventReceiver: SystemEventReceiver? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // Basics
        writeLogs()
        eatMemory()
        // Android components
        startMyService(applicationContext)
        dynamicBroadcastReceiver()
        addFragment(BlankFragment.newInstance("first", "second"))
        // Architecture patterns
        // SOLID
        createSingleRespVehicle()
        useOpenClosedPrinciple()
        useLiskovPrinciple()
        useInterfaceSegregation()
        useDependecyInversion()
    }

    private fun useDependecyInversion() {
        val isbnService = PublicationService(IsbnGenerator())
        val book1 = isbnService.createBook("Book with ISBN")
        val issnService = PublicationService(IssnGenerator())
        val book2 = issnService.createBook("Book with ISSN")
    }

    private fun useInterfaceSegregation() {
        val cat = Cat() //PetAnimal
        val lion = Lion() //WildAnimal
        cat.feed(); cat.groom()
        lion.feed(); // lion.groom()
    }

    private fun useLiskovPrinciple() {
        val duck = Duck()
        val ostrich = Ostrich()
        duck.fly()
        //ostrich.fly()
    }

    private fun useOpenClosedPrinciple() {
        val circle = Circle(20.5)
        val square = Square(40.5)
        val rect = Rectangle(20.5, 40.5)
        val shapesArray = arrayOf(circle, square, rect)
        val newAreaCalculator = AreaCalculator()
        val areasOfShapes = newAreaCalculator.calculateArea(shapes = shapesArray)
        Log.i(TAG + "shapeAreas", areasOfShapes.toString())
    }

    private fun createSingleRespVehicle() {
        val newCar = SingleResposibilityVehicle("ford");
        val newDriver = Driver("John");
        val newCarWash = CarWasher();
        val newMechanic = Mechanic();
        newDriver.drive(newCar);
        newCarWash.wash(newCar);
        val newTyre = Tyre("yokohama");
        newMechanic.changeTyre(newCar, newTyre);
        val carMake = newCar.getCarMake();
        Log.i(TAG + "newCar", newCar.getCarMake())
        Log.i(TAG + "newDriver", newDriver.getDriverName())
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
                //Log.d(TAG, MemoryEater(stringVal = "temp").toString())
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


