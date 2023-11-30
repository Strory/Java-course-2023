package edu.hw8.task2;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Logger;

public class FixedThreadPool implements ThreadPool {
    Logger logger = Logger.getLogger("FixedThreadPool");
    private final int poolSize;
    private final Thread[] threads;
    private final BlockingQueue<Runnable> taskQueue;

    public FixedThreadPool(int poolSize) {
        this.poolSize = poolSize;
        this.threads = new Thread[poolSize];
        this.taskQueue = new LinkedBlockingQueue<>();
    }

    @Override
    public void start() {
        for (int i = 0; i < poolSize; i++) {
            threads[i] = new Thread(new Worker());
            threads[i].start();
        }
    }

    @Override
    public void execute(Runnable runnable) {
        try {
            taskQueue.put(runnable);
        } catch (InterruptedException e) {
            logger.info(e.getMessage());
        }
    }

    @Override
    public void close() {
        for (Thread thread : threads) {
            thread.interrupt();
        }
    }

    public static ThreadPool create(int poolSize) {
        return new FixedThreadPool(poolSize);
    }

    private class Worker implements Runnable {
        @Override
        public void run() {
            while (true) {
                if (Thread.interrupted()) {
                    break;
                }
                try {
                    if (!(taskQueue.isEmpty())) {
                        Runnable task = taskQueue.take();
                        task.run();
                    }
                } catch (InterruptedException e) {
                    logger.info(e.getMessage());
                }
            }
        }
    }
}
