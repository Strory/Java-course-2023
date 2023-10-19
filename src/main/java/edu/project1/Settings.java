package edu.project1;

public class Settings {
    private final String[] words = new String[] {"violin", "piano", "drumms", "flute", "cello"};
    private final int maxAttempts = 5;
    private final String exitCommand = "quit";

    public String[] getWords() {
        return words;
    }

    public int getMaxAttempts() {
        return maxAttempts;
    }

    public String getExitCommand() {
        return exitCommand;
    }
}
