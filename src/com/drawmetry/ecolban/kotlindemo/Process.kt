package com.drawmetry.ecolban.kotlindemo

typealias Source<T> = () -> T
typealias Sink<T> = (T) -> Unit

class Process(private val source: Source<String>, private val sink: Sink<String>) : Runnable {
    private var isRunning: Boolean = false
    private var runner: Thread? = null

    override fun run() {
        while (isRunning) {
            try {
                val input = source()
                val output = process(input)
                sink(output)
            } catch (e: InterruptedException) {
                if (isRunning) {
                    println("Process interrupted while still running.")
                }
            }

        }
    }

    private fun process(input: String) = input.reversed()

    @Synchronized
    fun start() {
        isRunning = true
        runner = Thread(this)
        runner?.let() {
            it.isDaemon = true;
            it.start()
        }
    }

    @Synchronized
    fun stop() {
        isRunning = false
        runner?.interrupt()
    }
}
