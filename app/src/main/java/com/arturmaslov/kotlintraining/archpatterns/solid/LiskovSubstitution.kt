package com.arturmaslov.kotlintraining.archpatterns.solid

// subtypes must be substitutable from their base type

open class Bird
// substituted from base type
open class FlyingBirds : Bird() {
    fun fly() {}
}
// can fly
class Duck : FlyingBirds()
// can't fly. Shouldn't be able to use fly()
class Ostrich : Bird()