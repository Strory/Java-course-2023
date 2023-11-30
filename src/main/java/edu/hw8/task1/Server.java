package edu.hw8.task1;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

public class Server {
    Logger logger = Logger.getLogger("Server");
    private static final int PORT = 8080;
    private static final int MAX_CONNECTIONS = 2;

    public Server() {}

    public void runServer() {
        ExecutorService executorService = Executors.newFixedThreadPool(MAX_CONNECTIONS);

        try (ServerSocket serverSocket = new ServerSocket(PORT);) {
            while (true) {
                Socket socket = serverSocket.accept();
                executorService.execute(new ClientHandler(socket));
            }

        } catch (IOException e) {
            logger.info(e.getMessage());
        } finally {
            executorService.shutdown();
        }
    }
}
