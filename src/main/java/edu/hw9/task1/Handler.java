package edu.hw9.task1;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

public class Handler {
    private static Logger logger = Logger.getLogger("Handler");
    private static final int PRODUCER_THREAD_COUNT = 4;
    private static final int CONSUMER_THREAD_COUNT = 4;

    private Handler() {}

    @SuppressWarnings("MagicNumber")
    public static void process() {
        DataProvider provider = new DataGenerator();
        Queue<Metric> dataQueue = new ConcurrentLinkedQueue<>();
        StatsCollector collector = new StatsCollector();

        ExecutorService producer = Executors.newFixedThreadPool(PRODUCER_THREAD_COUNT);
        ExecutorService consumer = Executors.newFixedThreadPool(CONSUMER_THREAD_COUNT);

        for (int i = 0; i < PRODUCER_THREAD_COUNT; ++i) {
            producer.execute(new DataProducer(dataQueue, provider));
        }

        for (int i = 0; i < CONSUMER_THREAD_COUNT; ++i) {
            consumer.execute(new DataConsumer(dataQueue, collector));
        }

        while (collector.size() < 10) {
            // условие остановки переработки данных
        }

        producer.shutdown();
        consumer.shutdown();

        logger.info("Обработка завершена");
    }
}
