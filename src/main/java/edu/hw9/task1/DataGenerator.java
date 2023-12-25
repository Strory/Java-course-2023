package edu.hw9.task1;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;

public class DataGenerator implements DataProvider {
    private final int lengthLimit = 10;
    private final int metricFrom = 0;
    private final int metricTo = 1;
    private AtomicInteger nextMetricNameNumber = new AtomicInteger(0);

    public DataGenerator() {}

    private double[] getNumbers() {
        double[] metricNumbers = new double[ThreadLocalRandom.current().nextInt(1, lengthLimit)];
        for (int i = 0; i < metricNumbers.length; ++i) {
            metricNumbers[i] = ThreadLocalRandom.current().nextDouble(metricFrom, metricTo);
        }
        return metricNumbers;
    }

    @Override
    public Metric getMetric() {
        String name = "metric" + nextMetricNameNumber.incrementAndGet();
        return new Metric(name, getNumbers());
    }
}
