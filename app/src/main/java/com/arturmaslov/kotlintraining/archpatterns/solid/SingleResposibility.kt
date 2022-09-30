package com.arturmaslov.kotlintraining.archpatterns

// every class should have single direct responsibilities
data class SingleResposibilityVehicle(val make: String) {
    fun start() {}
    fun stop() {}
    fun getCarMake():String {return make}
}

class Driver(val name: String) {
    fun checkOilMeter(vehicle: SingleResposibilityVehicle) {}
    fun drive(vehicle: SingleResposibilityVehicle) {}
    fun getDriverName():String {return name}
}

class CarWasher {
    fun wash(vehicle: SingleResposibilityVehicle) {}
}

class Tyre(val make: String) {}
class Mechanic {
    fun changeTyre(vehicle: SingleResposibilityVehicle, tireNumber: Tyre) {}
    fun changeEngineOil(vehicle: SingleResposibilityVehicle) {}
}