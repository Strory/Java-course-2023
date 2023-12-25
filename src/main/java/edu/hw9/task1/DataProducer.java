package edu.hw9.task1;

import java.util.Queue;

public class DataProducer implements Runnable {
    private final Queue<Metric> dataQueue;
    private final DataProvider provider;

    public DataProducer(Queue<Metric> queue, DataProvider provider) {
        this.dataQueue = queue;
        this.provider = provider;
    }

    @Override
    public void run() {
        while (true) {
            Metric data = provider.getMetric();
            dataQueue.add(data);
        }
    }
}
