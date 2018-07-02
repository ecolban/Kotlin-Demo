package com.drawmetry.ecolban.kotlindemo;

public interface Source<T>  {
    T take() throws InterruptedException;
}
