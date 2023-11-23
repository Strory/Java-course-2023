package edu.hw7.task4;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class MonteCarloWithTreads {
    private final double r;
    private int circleCount = 0;
    private int totalCount = 0;

    MonteCarloWithTreads(double r) {
        this.r = r;
    }

    @SuppressWarnings("MagicNumber")
    public double calculatePi(int count, int threadsCount) {
        createThreads(count, threadsCount);
        return 4 * ((double) circleCount / totalCount);
    }

    private void createThreads(int count, int threadCount) {
        final int count2 = count / threadCount;
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < threadCount; ++i) {
            threads.add(new Thread(() -> {
                int totalPoints = 0;
                int circlePoints = 0;
                double mult = 2 * r;
                for (int j = 0; j < count2; ++j) {
                    double x = ThreadLocalRandom.current().nextDouble() * mult - r;
                    double y = ThreadLocalRandom.current().nextDouble() * mult - r;
                    ++totalPoints;
                    if (checkArea(x, y)) {
                        ++circlePoints;
                    }
                }
                synchronized (this) {
                    circleCount += circlePoints;
                    totalCount += totalPoints;
                }
            }));
        }
        for (Thread thread : threads) {
            thread.start();
        }
        try {
            for (Thread thread : threads) {
                thread.join();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private synchronized void createPoint(int count) {
        double mult = 2 * r;
        for (int i = 0; i < count; ++i) {
            double x = ThreadLocalRandom.current().nextDouble() * mult - r;
            double y = ThreadLocalRandom.current().nextDouble() * mult - r;
            ++totalCount;
            if (checkArea(x, y)) {
                ++circleCount;
            }
        }
    }

    private boolean checkArea(double x, double y) {
        return Math.sqrt(x * x + y * y) <= r;
    }
}
