package com.garmin.android.aqua

import java.lang.Exception

// Strings

fun testStrings() {
    val str = "abc"

    str.length
    str[0]
    str.substring(0, 2)

    val num = 6

    "My favorite number is $num"

    "My favorite number is ${num * 2}"


    for (letter in str) print("$letter ")

    str.forEach { letter -> print("$letter ") }
}

// Functions

fun isEven(num: Int) = num.rem(2) == 0

fun testFunctions() {

//fun consonants(s: String): String {
//    var result = ""
//    for (letter in s) {
//        if (letter != 'a' && letter != 'e' &&letter != 'i' &&letter != 'o' &&letter != 'u') result += letter
//    }
//    return result
//}

    fun consonants(s: String) = s.filterNot { it in "aeiouAEIOU" }

    consonants("fqwedadadad")

// Extension functions

    fun String.consonants() = filterNot { it in "aeiouAEIOU" }

    "fqwedadadad".consonants()

    fun isEven(num: Int) = num.rem(2) == 0
//isEven(5)

    fun Int.isEven() = rem(2) == 0
    5.isEven()
}
// Higher order functions (lambdas)


enum class Type {
    FOOD,
    OTHER
}
data class Product (val name: String, val type: Type, val unitPrice: Double, val quantity: Double = 1.0)

val apple = Product("apple", Type.FOOD, 2.5, 4.0)
val banana = Product("banana", Type.FOOD, 4.0)
val car = Product("car", Type.OTHER, 1000.0)



//fun calculatePrice(product: Product): Double = product.quantity * product.unitPrice
//fun calculatePrice(product: Product): Double = product.quantity * product.unitPrice * 119 / 100
//fun calculatePrice(product: Product): Double = product.quantity * product.unitPrice * (if (product.type == Type.FOOD) 105 else 119) / 100
//fun Product.calculatePrice(): Double = quantity * unitPrice * (if (type == Type.FOOD) 105 else 119) / 100

fun Product.calculatePrice(applyTax: (Product) -> Double) = quantity * unitPrice * applyTax(this)
//car.calculatePrice { product -> (if (product.type == Type.FOOD) 105 else 119) /100.0}

//fun Product.calculatePrice(applyTax: Product.() -> Double) = quantity * unitPrice * applyTax(this)
//
val romanianTax: Product.() -> Double = { (if (type == Type.FOOD) 105.0 else 119.0) / 100 }
val americanTax: Product.() -> Double = { 113.0/100 }

//car.calculatePrice{ (if (type == Type.FOOD) 105.0 else 119.0) / 100 }
//car.calculatePrice(romanianTax)
//car.calculatePrice(americanTax)

fun Product.calculatePrice(applyDiscount: Product.() -> Double, applyTax: Product.() -> Double)
        = quantity * unitPrice * applyDiscount(this) * applyTax(this)

val discount: Product.() -> Double = { (if (quantity > 3) 90.0 else 100.0) / 100 }

//apple.calculatePrice(discount, romanianTax)

// Scope functions (apply, run, let, also, with)

class RectangularShape(var x: Int, var y: Int, var with: Int, var height: Int, var  color: Int) {
    fun measure() {}
    fun render() {}
}

fun initShape(shape: RectangularShape?) {
    // Good
    shape?.apply {
        x = 10; y = 20
        with = 100; height = 200
        color = 0xFF0066
    } ?: throw IllegalArgumentException()

    // Bad
    if (shape != null) {
        shape.x = 10
        shape.y = 20
        shape.with = 100
        shape.height = 200
        shape.color = 0xFF0066
    } else {
        throw IllegalArgumentException()
    }
}

inline fun <T> T.apply(block: T.() -> Unit): T {
    block()
    return this
}


// Lists

fun tesLists() {
    val data = listOf(4, 9, 8)

    val data2 = listOf(76, 4, 0)

    data + data2

    data[0]

    for (element in data) print("$element ")

    data.forEach { print("$it ") }

    fun Int.isEven() = rem(2) == 0

    data.map { it.isEven() }.forEach { print("$it ") }

    data.filter { it.isEven() }
}

// Objects

object DatabaseConnection {
    fun getData(): String = ""
}

fun tetObjects() {

    DatabaseConnection.getData()
}

// Enums, Sealed classes

sealed class ServerResponse {
    class Success(val result: String) : ServerResponse()
    class Error(val exception: Exception) : ServerResponse()
    object NoInternet : ServerResponse()
}

enum class Direction {
    N,
    S,
    E,
    W
}

fun testEnums() {
    fun getDirectionString(dir: Direction) = when (dir) {
        Direction.N -> "North"
        Direction.E -> "North"
        Direction.W -> "North"
        Direction.S -> "North"
    }

    getDirectionString(Direction.N)

    fun handleResponse(response: ServerResponse) = when (response) {
        is ServerResponse.Success -> print(response.result)
        is ServerResponse.Error -> throw response.exception
        ServerResponse.NoInternet -> print("No Internet!!!")
    }
}














