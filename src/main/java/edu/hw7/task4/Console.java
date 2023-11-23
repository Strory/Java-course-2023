package edu.hw7.task4;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class Console {
    private Console() {}

    public static Logger logger = Logger.getLogger("Console");

    @SuppressWarnings({"MagicNumber", "MultipleVariableDeclarations", "MultipleVariableDeclarations"})
    public static void monitor() {
        MonteCarloMethod method = new MonteCarloMethod(500);
        MonteCarloWithTreads methodTh = new MonteCarloWithTreads(500);

        double pi;
        long startTime;
        long endTime;
        long executionTime;
        double averageAccelerationTime1, averageAccelerationTime2, averageAccelerationTime3, averageAccelerationTime4;
        double justTotalTime = 0;
        double threadsTotalTime1 = 0;
        double threadsTotalTime2 = 0;
        double threadsTotalTime3 = 0;
        double threadsTotalTime4 = 0;
        int iterations = 1_000_000_000;
        List<Double> errors = new ArrayList<>();

        for (int i = 0; i < 10; ++i) {
            startTime = System.nanoTime();
            pi = method.calculatePi(iterations);
            endTime = System.nanoTime();
            executionTime = endTime - startTime;
            justTotalTime += executionTime;
            errors.add(Math.abs(Math.PI - pi));

            startTime = System.nanoTime();
            pi = methodTh.calculatePi(iterations, 1);
            endTime = System.nanoTime();
            executionTime = endTime - startTime;
            threadsTotalTime1 += executionTime;
            errors.add(Math.abs(Math.PI - pi));

            startTime = System.nanoTime();
            pi = methodTh.calculatePi(iterations, 2);
            endTime = System.nanoTime();
            executionTime = endTime - startTime;
            threadsTotalTime2 += executionTime;
            errors.add(Math.abs(Math.PI - pi));

            startTime = System.nanoTime();
            pi = methodTh.calculatePi(iterations, 3);
            endTime = System.nanoTime();
            executionTime = endTime - startTime;
            threadsTotalTime3 += executionTime;
            errors.add(Math.abs(Math.PI - pi));

            startTime = System.nanoTime();
            pi = methodTh.calculatePi(iterations, 4);
            endTime = System.nanoTime();
            executionTime = endTime - startTime;
            threadsTotalTime4 += executionTime;
            errors.add(Math.abs(Math.PI - pi));
        }
        averageAccelerationTime1 = (justTotalTime / 10) / (threadsTotalTime1 / 10);
        averageAccelerationTime2 = (justTotalTime / 10) / (threadsTotalTime2 / 10);
        averageAccelerationTime3 = (justTotalTime / 10) / (threadsTotalTime3 / 10);
        averageAccelerationTime4 = (justTotalTime / 10) / (threadsTotalTime4 / 10);

        double averageError = errors.stream().reduce(1d, Double::sum) / errors.size();
        logger.info(String.valueOf(averageError));

        logger.info(String.valueOf(averageAccelerationTime1));
        logger.info(String.valueOf(averageAccelerationTime2));
        logger.info(String.valueOf(averageAccelerationTime3));
        logger.info(String.valueOf(averageAccelerationTime4));
    }
}
