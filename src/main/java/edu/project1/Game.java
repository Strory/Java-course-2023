package edu.project1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Game {
    private final String word;
    private final Settings settings = new Settings();
    private final User user;

    public Game() {
        int numberWord = 0;
        this.word = this.settings.getWords()[numberWord];
        this.user = new User(this.word.length(), this.settings.getMaxAttempts());
    }

    public void mainCycle() {
        while (user.getAttempts() > 0) {
            System.out.println("Guess a letter:");
            String answer = _userInput();
            if (answer.equals(settings.getExitCommand())) {
                return;
            }
            if (answer.length() != 1) {
                System.out.println("Error input!");
                continue;
            }
            if (word.contains(answer)) {
                System.out.println("Hit!\n");
                char letter = answer.charAt(0);
                user.addLetter(letter, word);
                System.out.print("The word: " + user.getUserAnswer() + "\n\n");
            } else {
                user.takeAwayAttempt();
                System.out.println("Missed, mistake " + (settings.getMaxAttempts() - user.getAttempts()) +
                        " out of " + settings.getMaxAttempts() + ".");
            }
            if (word.equals(user.getUserAnswer())){
                break;
            }
        }
        if (user.getAttempts() > 0) {
            System.out.println("You won!");
        } else {
            System.out.println("You lost!");
        }
    }

    private String _userInput() {
        String inputLine;
        try {
            InputStreamReader isr = new InputStreamReader(System.in);
            BufferedReader reader = new BufferedReader(isr);
            inputLine = reader.readLine();
        } catch (IOException e) {
            inputLine = "";
        }
        return inputLine;
    }
}
