package edu.project1;

import java.util.Arrays;

public class User {
    private int attempts;
    private char[] userAnswer;

    public User(int userAnswerLength, int attempts) {
        this.userAnswer = new char[userAnswerLength];
        Arrays.fill(this.userAnswer, '*');
        this.attempts = attempts;
    }

    public void changeAnswer(int index, char symbol) {
        this.userAnswer[index] = symbol;
    }

    public String getUserAnswer() {
        return new String(userAnswer);
    }

    public void addLetter(char letter, String word) {
        int index = word.indexOf(letter);
        while (index != -1) {
            userAnswer[index] = letter;
            index = word.indexOf(letter, index + 1);
        }
    }

    public void takeAwayAttempt() {--attempts;}

    public int getAttempts() {return attempts;}

}
