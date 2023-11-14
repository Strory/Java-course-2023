package edu.hw6.Task6;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class Task6 {
    private Task6() {}

    static Logger logger = Logger.getLogger("portLogger");

    @SuppressWarnings({"MagicNumber", "MultipleStringLiterals"})
    public static void scanServerSocket() {
        Map<Integer, String> famousPorts = new HashMap<>(Map.of(
            80, "HTTP (HyperText Transfer Protocol)",
            137, "NetBIOS protocol for network communication between Windows devices.",
            138, "NetBIOS protocol for network communication between Windows devices.",
            139, "NetBIOS protocol for network communication between Windows devices.",
            445, "Server Message Block protocol for file sharing and printer sharing."
        ));
        int endSocket = 49151;
        for (int i = 0; i <= endSocket; ++i) {
            try {
                ServerSocket serverSocket = new ServerSocket(i);
                DatagramSocket datagramSocket = new DatagramSocket(i);
                logger.info(i + "\t");
            } catch (IOException e) {
                String message;
                if (famousPorts.containsKey(i)) {
                    message = i + "\t" + famousPorts.get(i);
                } else {
                    message = i + "\t";
                }
                logger.info(message);
            }
        }
    }
}
