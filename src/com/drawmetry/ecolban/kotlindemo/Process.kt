package com.drawmetry.ecolban.kotlindemo

typealias Source<T> = () -> T
typealias Sink<T> = (T) -> Unit

class Process(private val source: Source<out String>, private val sink: Sink<in String>) : Runnable {
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

    private fun process(input: String): String {
        val sb = StringBuilder()
        for (i in input.length - 1 downTo 0) {
            sb.append(input[i])
        }
        return sb.toString()
    }

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
