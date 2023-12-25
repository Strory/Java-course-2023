package edu.hw9.task1;

import java.util.Queue;

public class DataConsumer implements Runnable {
    private final Queue<Metric> dataQueue;
    private final StatsCollector collector;

    public DataConsumer(Queue<Metric> queue, StatsCollector collector) {
        this.dataQueue = queue;
        this.collector = collector;
    }

    @Override
    public void run() {
        while (true) {
            if (!dataQueue.isEmpty()) {
                Metric data = dataQueue.poll();
                collector.push(data.name(), data.numbers());
            }
        }
    }
}
