package edu.project1;

public class TestCommandController {
    private final String[] commandList;
    private int iter = 0;

    public TestCommandController(String[] commands) {
        this.commandList = commands;
    }

    // Служебные коды, которые возвращает игра:

    // 0 - код выхода
    // 1 - код ошибки ввода
    // 2 - код попадания
    // 3 - код промаха
    // 4 - код победы
    // 5 - код поражения

    public static String commandSender(String[] commands) {
        Game gameSession = new Game();
        TestCommandController controller = new TestCommandController(commands);
        StringBuilder gameAnswers = new StringBuilder();
        while (true) {
            String answer = controller.input();
            String[] gameAnswer = gameSession.eventListener(answer).split(":");
            gameAnswers.append(gameAnswer[0]);
            if (gameAnswer[0].equals("0")) {
                return gameAnswers.toString();
            }
            if (gameAnswer[0].equals("4")) {
                return gameAnswers.toString();
            }
            if (gameAnswer[0].equals("5")) {
                return gameAnswers.toString();
            }
        }
    }

    private String input() {
        return commandList[iter++];
    }
}
