package edu.hw7.task3;

public class PeopleDeleter extends Thread {
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
                sleep(5);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            database.delete(i);
        }
    }
}
