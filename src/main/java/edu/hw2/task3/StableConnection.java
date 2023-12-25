package edu.hw2.task3;

import java.util.logging.Logger;

class StableConnection implements Connection {
    private final static Logger LOGGER = Logger.getLogger("connectLogger");

    @Override
    public void execute(String command) {
        LOGGER.info("Стабильное соединение");
    }

    @Override
    public void close() throws Exception {
        LOGGER.info("Закрытие соединения");
    }
}
