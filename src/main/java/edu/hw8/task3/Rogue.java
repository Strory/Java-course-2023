package edu.hw8.task3;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class Rogue {
    Logger logger = Logger.getLogger("rogue");
    private final Map<String, String> database;
    private final int charactersCount = 63;
    private final long maxCount = 15_752_961;
    private final String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789_";
    private Map<String, String> decrypted = new HashMap<>();

    public Rogue(Map<String, String> database) {
        this.database = database;
    }

    public void demonstration() {
        decrypt();
        for (var entry : decrypted.entrySet()) {
            logger.info(entry.getKey() + " " + entry.getValue());
        }
    }

    @SuppressWarnings("ReturnCount")
    private void decrypt() {
        if (database.isEmpty()) {
            return;
        }
        for (int i = 0; i < maxCount; ++i) {
            String password = nextPassword(i);
            String hash = HashFunctions.getHash(password);
            if (database.containsKey(hash)) {
                decrypted.put(password, database.get(hash));
                database.remove(hash);

                if (database.isEmpty()) {
                    return;
                }
            }
        }
    }

    @SuppressWarnings("ParameterAssignment")
    private String nextPassword(long stringKey) {
        final int lettersCount = 4;
        StringBuilder sb = new StringBuilder(lettersCount);
        for (int i = 0; i < lettersCount; ++i) {
            sb.append(characters.charAt((int) stringKey % charactersCount));
            stringKey /= charactersCount;
        }
        return sb.toString();
    }
}
