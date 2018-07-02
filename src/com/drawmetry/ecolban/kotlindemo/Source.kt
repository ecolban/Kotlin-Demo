package com.drawmetry.ecolban.kotlindemo

interface Source<T> {
    @Throws(InterruptedException::class)
    fun take(): T
}
