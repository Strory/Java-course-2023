package edu.hw8.task3;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class SomeDatabase {
    private SomeDatabase() {}

    public static Map<String, String> getDatabase() {
        final int count = 5000;
        Map<String, String> database = new HashMap<>();
        for (int i = 0; i < count; ++i) {
            String name = "name" + i;
            String hash = HashFunctions.getHash(generateRandomPassword());
            database.put(hash, name);
        }
        return database;
    }

    @SuppressWarnings("MagicNumber")
    private static String generateRandomPassword() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789_";
        Random rand = new Random();
        int length = 4;
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(characters.charAt(rand.nextInt(characters.length())));
        }
        return sb.toString();
    }
}
