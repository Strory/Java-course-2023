package edu.hw7.task1;

import java.util.concurrent.atomic.AtomicInteger;

public class Incrementer extends Thread {

    private AtomicInteger counter = Task1.counter;

    @Override
    @SuppressWarnings("MagicNumber")
    public void run() {
        for (int i = 0; i < 100_000; ++i) {
            counter.incrementAndGet();
        }
    }
}
