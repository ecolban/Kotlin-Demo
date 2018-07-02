package com.drawmetry.ecolban.kotlindemo

import org.junit.Test

import java.util.concurrent.ArrayBlockingQueue
import java.util.concurrent.BlockingQueue

import junit.framework.TestCase.assertEquals

class TestProcess {

    @Test
    @Throws(InterruptedException::class)
    fun testProcess() {
        val inQ = ArrayBlockingQueue<String>(2)
        val outQ = ArrayBlockingQueue<Any>(2)
        inQ.put("Hello world")
        val source = Source { inQ.take() }
        val sink = Sink<Any> { outQ.put(it) }
        val sut = Process(source, sink)
        sut.start()
        val s = outQ.take() as String
        assertEquals("dlrow olleH", s)
        sut.stop()
    }
}
