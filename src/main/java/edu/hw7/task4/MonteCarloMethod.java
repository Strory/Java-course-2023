package edu.hw7.task4;

import java.util.concurrent.ThreadLocalRandom;

public class MonteCarloMethod {
    private final double r;
    private int circleCount = 0;
    private int totalCount = 0;

    MonteCarloMethod(double r) {
        this.r = r;
    }

    @SuppressWarnings("MagicNumber")
    public double calculatePi(int count) {
        createPoint(count);
        return 4 * ((double) circleCount / totalCount);
    }

    private void createPoint(int count) {
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
