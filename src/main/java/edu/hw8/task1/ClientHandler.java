package edu.hw8.task1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.logging.Logger;

public class ClientHandler implements Runnable {

    private Logger logger = Logger.getLogger("ClientHandler");
    private Socket socket;

    private ClientHandler() {}

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            String clientMessage;
            while ((clientMessage = reader.readLine()) != null) {
                String response = Respondent.getPhrase(clientMessage) + "\n";
                writer.write(response);
                writer.flush();
            }
            socket.close();
        } catch (IOException e) {
            logger.severe(e.getMessage());
        }
    }
}
