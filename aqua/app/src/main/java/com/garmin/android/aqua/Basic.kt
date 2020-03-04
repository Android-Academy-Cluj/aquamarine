package com.garmin.android.aqua

//variables
val x = 5  //read-only variables
var y = 10 //variables that can reassigned

//basic types - in kotlin everything is an object
val three = 3 //Int
val pi = 3.14 //Double

//you can also declare them explicitely
val a: Int = 4
val text: String = "a"

//type inference: automatically identifies type
//Kotlin supports type inference
val language = "Kotlin"

//Kotlin aims to eliminate NullPointerException
//nullability checks
//In Kotlin we distinguish between references that can hold null and those that can not hold null
var word: String? = null

//safe call operator
//e.g. length property
var length = word?.length //returns null if word is null

//elvis operator ?:
//If the expression to the left of ?: is not null, the elvis operator returns it, otherwise it returns the expression to the right
val l = if (word != null) word?.length else -1 //or
val size = word?.length ?: -1

//Functions
//can be declared at top level in a file, meaning you do not need to create a class to hold functions (top level functions)
// are declared using fun word
fun sumOfTwoNumbers(x: Int, y: Int): Int {
    return x + y
}

//functions can have default arguments, which are used when a corresponding argument is not provided
//if a function does not return any useful value, its return type is Unit, word which can be omitted from the function signature; otherwise return type must me specified
fun printTwoWords(x: String, y: String = "a") {
    println("First string is: $x, second string is: $y")
}

//control flow: if, when, for, while
val five = 5
val four = 4

fun getHighestNumber(): Int {
    if (five > four) {
        return five
    } else return four
    return if (five > four) five else four
}

//or

fun getHighestNumber2() = if (five > four) five else four

//when replaces switch
fun useOfWhen(x: Int = 5): Int {
    return when (x) {
        1 -> 1
        2 -> 2
        else -> 5
    }
}

//for loop
//iterates through anything that provides an iterator (has a member iterator() who has a member next() and a member hasNext() that returns Boolean)
fun useOfFor() {
    for (i in 1..3) {
        print(i)
    }

    //in a collection
    val collection = arrayListOf(1, 2, 3, 4, 5)
    for (i in collection) {
        print(i)
    }
}

//classes in Kotlin are declared using "class" keyboard
class MyClass {}
//to create an instance
//val object= MyClass()

//classes can have a primary constructor and one or more secondary constructors
//if constructor does not have any visibility modifiers, word "constructor" can be omitted
//properties can be read-only (var) or reassigned(val)`
data class Person constructor(val name: String, val surname: String) {
    var age: Int = 0

    //lateinit: property initialization feature; means the property is declared here but it is later initialized when used for the first time
    //lateinit: is only used with var properties which must be non-null
    //if you access the property without it being initialized, it will throw "UnitializedPropertyAccessException"
    lateinit var address: String

    //secondary constructor
    constructor(surname: String) : this("John", surname)

    fun setAddress() {
        address = "No.8 Main Street"
    }
}

//Data classes: classes that have the main purpose to hold data
//compiler declares the following members from all properties in the primary constructor: equals(),
//hashCode(), toString()

//conditions for a class to be a data class:
//- the primary constructor needs to have at leas one parameter
//- primary constructor parameters need to be either var or val
//- class can not be abstract, sealed, inner or open
data class Student(val name: String)

//Inheritance and overriding methods

//a class which has methods which will be overridden, needs to be declared open,
// Also the methods which should be overridden need to be declared open
//An abstract class is open by default so the annotation is not needed
open class Bicycle(val speed: Int, val gear: Int) {
    //a method which you can override has to be declared with open
    open fun getName() = "Bicycle"
}

//Derived class: if the derived class has a primary constructor, then the base class must be initialized there using
//the parameters of the primary constructor
class Tricycle(speed: Int, gear: Int) : Bicycle(speed, gear) {
    override fun getName(): String {
        return "Tricycle"
    }
}
