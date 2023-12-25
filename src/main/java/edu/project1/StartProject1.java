package edu.project1;

public class StartProject1 {
    private StartProject1() {
    }

    // Этот класс не нужен. Он находится здесь, чтобы сторонний пользователь
    // мог запустить игру

    @SuppressWarnings("UncommentedMain")
    public static void main(String[] args) {
        GameController.commandSender();
    }
}
