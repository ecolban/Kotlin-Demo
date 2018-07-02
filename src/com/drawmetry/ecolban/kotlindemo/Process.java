package com.drawmetry.ecolban.kotlindemo;

public class Process implements Runnable {

    private final Source<? extends String> source;
    private final Sink<? super String> sink;
    private boolean isRunning;
    private Thread runner;

    public Process(Source<? extends String> source, Sink<? super String> sink) {
        this.source = source;
        this.sink = sink;
    }

    @Override
    public void run() {
        while (isRunning) {
            try {
                String input = source.take();
                String output = process(input);
                sink.put(output);
            } catch (InterruptedException e) {
                if (isRunning) {
                    System.out.println("Process interrupted while still running.");
                }
            }
        }
    }

    private String process(String input) {
        StringBuilder sb = new StringBuilder();
        for (int i = input.length() - 1; i >= 0; i--) {
            sb.append(input.charAt(i));
        }
        return sb.toString();
    }

    public synchronized void start() {
        runner = new Thread(this);
        runner.setDaemon(true);
        isRunning = true;
        runner.start();
    }

    public synchronized void stop() {
        isRunning = false;
        runner.interrupt();
    }
}
