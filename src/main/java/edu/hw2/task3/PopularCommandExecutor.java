package edu.hw2.task3;

import java.util.logging.Logger;

public class PopularCommandExecutor {
    private final static Logger LOGGER = Logger.getLogger("commandLogger");
    private final ConnectionManager manager;
    private final int maxAttempts;

    public PopularCommandExecutor(ConnectionManager manager, int maxAttempts) {
        this.manager = manager;
        this.maxAttempts = maxAttempts;
    }

    public void updatePackages() {
        tryExecute("apt update && apt upgrade -y");
    }

    void tryExecute(String command) {
        for (int i = 1; i <= maxAttempts; ++i) {
            try (Connection connection = manager.getConnection()) {
                connection.execute(command);
                return;
            } catch (ConnectionException e) {
                LOGGER.info("Обработка исключения.");
                if (i >= maxAttempts) {
                    throw new ConnectionException("Превышено количество попыток", e);
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
