package com.arturmaslov.kotlintraining.archpatterns.solid

// interface should not have methods that implementing classes do not require
// We should not force a class to implement methods which are not required

interface Animal {
    fun feed()
}

// role interfaces
interface WildAnimal : Animal
interface PetAnimal : Animal {
    fun groom()
}

class Cat : PetAnimal {
    override fun feed() {
        // Add code to feed the Cat
    }

    override fun groom() {
        // Add code to Groom the Cat
    }
}

class Lion : WildAnimal {
    override fun feed() {
        // Add code to feed the Lion
    }
}