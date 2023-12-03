package edu.hw9.task1;

import java.util.Arrays;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class StatsCollector {
    private ConcurrentMap<String, Stat> stats = new ConcurrentHashMap<>();
    private final String emptyMessage = "metrics is empty";

    public void push(String name, double[] metrics) {
        if (name.equals(null)) {
            return;
        }
        Stat stat = new Stat(getSum(metrics), getAverage(metrics), getMax(metrics),
            getMin(metrics));
        stats.put(name, stat);
    }

    public int size() {
        return stats.size();
    }

    private double getSum(double[] metrics) {
        if (metrics.length == 0) {
            throw new IllegalArgumentException(emptyMessage);
        }
        return Arrays.stream(metrics).sum();
    }

    private double getAverage(double[] metrics) {
        if (metrics.length == 0) {
            throw new IllegalArgumentException(emptyMessage);
        }
        return Arrays.stream(metrics).average().getAsDouble();
    }

    private double getMax(double[] metrics) {
        if (metrics.length == 0) {
            throw new IllegalArgumentException(emptyMessage);
        }
        return Arrays.stream(metrics).max().getAsDouble();
    }

    private double getMin(double[] metrics) {
        if (metrics.length == 0) {
            throw new IllegalArgumentException(emptyMessage);
        }
        return Arrays.stream(metrics).min().getAsDouble();
    }
}
