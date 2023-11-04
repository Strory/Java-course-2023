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
}
