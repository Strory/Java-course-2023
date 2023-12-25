package edu.hw7.task1;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;

public class Task1 {

    private Task1() {}

    public static AtomicInteger counter = new AtomicInteger(0);
    private static Logger logger = Logger.getLogger("logger");

    public static int incrementer() {
        Thread incrementer1 = new Incrementer();
        Thread incrementer2 = new Incrementer();
        incrementer1.start();
        incrementer2.start();
        try {
            incrementer1.join();
            incrementer2.join();
        } catch (InterruptedException error) {
            logger.severe(error.getMessage());
        }
        return Integer.parseInt(String.valueOf(counter));
    }
}
