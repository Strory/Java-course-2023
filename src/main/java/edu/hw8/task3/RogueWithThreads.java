package edu.hw8.task3;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.logging.Logger;

public class RogueWithThreads {
    Logger logger = Logger.getLogger("RogueWithThreads");
    private final Map<String, String> database;
    private final int charactersCount = 63;
    private final long maxCount = 15_752_961;
    private final String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789_";
    private ConcurrentMap<String, String> decrypted = new ConcurrentHashMap<>();

    public RogueWithThreads(Map<String, String> database) {
        this.database = new ConcurrentHashMap<>(database);
    }

    public Map<String, String> getDatabase() {
        divide();
        return decrypted;
    }

    @SuppressWarnings("MagicNumber")
    private void divide() {
        long from = 0;
        long to = maxCount / 4;
        long step =  maxCount / 4;
        Thread[] threads = new Thread[4];
        for (int i = 0; i < 3; ++i) {
            long finalFrom = from;
            long finalTo = to;
            threads[i] = new Thread(() -> decrypt(finalFrom, finalTo));
            from += step;
            to += step;
        }
        long finalFrom1 = from;
        threads[3] = new Thread(() -> decrypt(finalFrom1, maxCount));

        for (Thread thread : threads) {
            thread.start();
        }

        try {
            for (Thread thread : threads) {
                thread.join();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void demonstration() {
        divide();
        for (var entry : decrypted.entrySet()) {
            logger.info(entry.getKey() + " " + entry.getValue());
        }
    }

    @SuppressWarnings("ReturnCount")
    private void decrypt(long from, long to) {
        if (database.isEmpty()) {
            return;
        }
        for (long i = from; i < to; ++i) {
            String password = nextPassword(i);
            String hash = HashFunctions.getHash(password);
            if (database.containsKey(hash)) {
                decrypted.put(password, database.get(hash));
                database.remove(hash);
            }
            if (database.isEmpty()) {
                return;
            }
        }
    }

    @SuppressWarnings({"ParameterAssignment", "MagicNumber"})
    private String nextPassword(long stringKey) {
        int lettersCount = 4;
        StringBuilder sb = new StringBuilder(lettersCount);
        for (int i = 0; i < lettersCount; ++i) {
            sb.append(characters.charAt((int) stringKey % charactersCount));
            stringKey /= charactersCount;
        }
        return sb.toString();
    }
}
