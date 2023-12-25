package edu.hw2.task3;

public class DefaultConnectionManager implements ConnectionManager {
    private int connectNumber = 0;

    public DefaultConnectionManager() {
    }

    @SuppressWarnings("MagicNumber")
    @Override
    public Connection getConnection() {
        ++connectNumber;
        return (!(connectNumber % 3 == 0)) ? new FaultyConnection() : new StableConnection();
    }
}
