package edu.project1;

public class Game {
    private final String word;
    private final Settings settings = new Settings();
    private final User user;

    public Game() {
        int numberWord = 0;
        this.word = this.settings.getWords()[numberWord];
        this.user = new User(this.word.length(), this.settings.getMaxAttempts());
    }

    @SuppressWarnings("ReturnCount")
    public String eventListener(String answer) {
        if (answer.equals(settings.getExitCommand())) {
            return "0";
        }
        if (answer.length() != 1) {
            return "1";
        }
        if (word.contains(answer)) {
            char letter = answer.charAt(0);
            user.addLetter(letter, word);
            if (word.equals(user.getUserAnswer())) {
                return "4:" + user.getUserAnswer();
            }
            return "2:" + user.getUserAnswer();
        } else {
            user.reduceAttempt();
            if (user.getAttempts() < 1) {
                return "5:" + ":" + (settings.getMaxAttempts() - user.getAttempts())
                    + ":" + settings.getMaxAttempts();
            }
            return "3:" + (settings.getMaxAttempts() - user.getAttempts())
                + ":" + settings.getMaxAttempts();
        }
    }

    public int getAttempts() {
        return user.getAttempts();
    }
}
