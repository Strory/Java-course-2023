package edu.hw8.task2;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;

public class Fibonacci {
    static Logger logger = Logger.getLogger("logger");

    private Fibonacci() {
    }

    @SuppressWarnings("MagicNumber")
    public static void calculateWithThreads() {
        AtomicInteger numbersCount = new AtomicInteger(0);
        int maxN = 50;
        try {
            ThreadPool threadPool = FixedThreadPool.create(4);
            for (int i = 0; i < maxN; ++i) {
                final int n = i;
                threadPool.execute(() -> {
                    long result = Fibonacci.compute(n);
                    logger.info(n + ": " + result);
                    numbersCount.incrementAndGet();
                });
            }
            threadPool.start();

            while (numbersCount.get() != maxN) {
            }
            threadPool.close();
        } catch (Exception e) {
            logger.info(e.getMessage());
        }
    }

    public static long compute(int n) {
        if (n == 0) {
            return 0;
        } else if (n == 1) {
            return 1;
        }

        return compute(n - 1) + compute(n - 2);
    }
}
