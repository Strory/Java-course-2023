package edu.project2;

import java.util.Random;

public class GenerateNumbers {
    private GenerateNumbers() {}

    public static Random randomNumber = new Random();

    public static int nextId = 1;

    public static int getRandomBinaryNumber() {
        return randomBinaryNumber();
    }

    public static int getId() {
        return nextId++;
    }

    @SuppressWarnings("MagicNumber")
    public static int randomBinaryNumber() {
        return (randomNumber.nextInt(100) > 50) ? 1 : 0;
    }

    @SuppressWarnings("MagicNumber")
    public static int randomFourNumber() {
        int rand = randomNumber.nextInt(100);
        if (rand > 75) {
            return 4;
        } else if (rand > 50) {
            return 3;
        } else if (rand > 25) {
            return 2;
        } else {
            return 1;
        }
    }

    public static int randomInt(int limit) {
        Random rand = new Random();
        return rand.nextInt(limit);
    }
}
