package com.drawmetry.ecolban.kotlindemo

interface Sink<T> {
    @Throws(InterruptedException::class)
    fun put(something: T)
}
