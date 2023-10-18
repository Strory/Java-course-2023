package edu.hw2.task3;

class FaultyConnection implements Connection {
    private static int faultyConnectCount = 0;
    private final int faultyConnectNumber;

    FaultyConnection() {
        ++faultyConnectCount;
        faultyConnectNumber = faultyConnectCount;
    }

    @SuppressWarnings("MagicNumber")
    @Override
    public void execute(String command) {
        if (!(faultyConnectNumber % 5 == 0)) {
            throw new ConnectionException();
        }
    }

    @Override
    public void close() throws Exception {
        // Закрытие соединения
    }
}
