package com.dog.sunshine.util

fun getImperialTemp(temp: Int): Int {
    return (temp * 1.8 + 32).toInt()
}

fun getImperialDewPoint(value: Float): Float{
    return value*2.5F
}