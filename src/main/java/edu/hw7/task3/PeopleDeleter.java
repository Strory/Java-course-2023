package edu.hw7.task3;

import java.util.logging.Logger;

public class PeopleDeleter extends Thread {
    private Logger logger = Logger.getLogger("PeopleDeleter");
    private final PersonDatabase database;
    private final int from;
    private final int to;

    public PeopleDeleter(PersonDatabase database, int from, int to) {
        this.database = database;
        this.from = from;
        this.to = to;
    }

    @Override
    @SuppressWarnings("MagicNumber")
    public void run() {
        for (int i = from; i <= to; ++i) {
            try {
                logger.info("delete person");
                sleep(5);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            database.delete(i);
        }
    }
}
