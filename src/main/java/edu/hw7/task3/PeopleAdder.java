package edu.hw7.task3;

import java.util.List;
import static java.lang.Thread.sleep;

public class PeopleAdder extends Thread {
    private final PersonDatabase database;
    private final List<Person> data;
    private final int from;
    private final int to;

    public PeopleAdder(PersonDatabase database, List<Person> data, int from, int to) {
        this.database = database;
        this.data = data;
        this.from = from;
        this.to = to;
    }

    @Override
    public void run() {
        for (int i = from; i <= to; ++i) {
            database.add(data.get(i));
            try {
                sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
