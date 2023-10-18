package edu.hw2.task3;

public class PopularCommandExecutor {
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
            try {
                Connection connection = manager.getConnection();
                connection.execute(command);
                connection.close();
                return;
            } catch (ConnectionException e) {
                // обработка исключения
                if (i >= maxAttempts) {
                    throw new ConnectionException("Превышено количество попыток", e);
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
