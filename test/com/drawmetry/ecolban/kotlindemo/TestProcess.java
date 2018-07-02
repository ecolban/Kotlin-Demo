package com.drawmetry.ecolban.kotlindemo;

import org.junit.Test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import static junit.framework.TestCase.assertEquals;

public class TestProcess {

    @Test
    public void testProcess() throws InterruptedException {
        BlockingQueue<String> inQ = new ArrayBlockingQueue<>(2);
        BlockingQueue<Object> outQ = new ArrayBlockingQueue<>(2);
        inQ.put("Hello world");
        Source<String> source = inQ::take;
        Sink<Object> sink = outQ::put;
        Process sut = new Process(source, sink);
        sut.start();
        String s = (String) outQ.take();
        assertEquals("dlrow olleH", s);
        sut.stop();
    }
}
