package edu.hw8.task1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Logger;

public class Client {

    private Logger logger = Logger.getLogger("client");
    private static final String SERVER_ADDRESS = "127.0.0.1";
    private static final int SERVER_PORT = 8080;

    public Client() {}

    @SuppressWarnings("MagicNumber")
    public void runClient() {
        try (Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT)) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));

            String[] topics = new String[5];
            topics[0] = "личности";
            topics[1] = "оскорбления";
            topics[2] = "глупый";
            topics[3] = "интеллект";
            topics[4] = "somePhrase";

            int n = ThreadLocalRandom.current().nextInt(5);
            String message = topics[n];
            writer.write(message + "\n");
            writer.flush();

            String serverResponse = reader.readLine();
            logger.info(serverResponse);
        } catch (IOException e) {
            logger.severe(e.getMessage());
        }
    }
}
