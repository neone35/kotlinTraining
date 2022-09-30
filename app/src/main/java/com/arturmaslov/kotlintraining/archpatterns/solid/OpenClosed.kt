package com.arturmaslov.kotlintraining.archpatterns.solid

// The open-closed principle says that a class should be open for extension
// and closed for modifications

// did not change anything & open for extension
// interfaces are recommended choices over classes because class extension is limited
interface Shape {
    fun area(): Double
}

class Circle(val radius: Double) : Shape {
    override fun area(): Double {
        return 3.14 * radius * radius
    }
}

class Square(val area: Double) : Shape {
    override fun area(): Double {
        return area * area
    }
}

class Rectangle(val width: Double, val height: Double) : Shape
{
    override fun area(): Double {
        return width * height
    }
}

// did not change anything
class AreaCalculator {
    fun calculateArea(shapes: Array<Shape>): List<String> {
        val areasArray = mutableListOf<String>();
        shapes.forEach { shape ->
            // Calculate area of shape
            val area = shape.area()
            // Do something with calculated area
            areasArray.add(area.toString())
        }
        return areasArray
    }
}