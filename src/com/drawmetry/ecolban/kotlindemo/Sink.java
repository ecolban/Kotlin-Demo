package com.drawmetry.ecolban.kotlindemo;

public interface Sink<T> {
    void put(T something) throws InterruptedException;
}
