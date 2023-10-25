package edu.project1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Logger;

public class GameController {
    private final static Logger LOGGER = Logger.getLogger("gameLogger");

    public GameController() {
    }

    // Служебные коды, которые возвращает игра:

    // 0 - код выхода
    // 1 - код ошибки ввода
    // 2 - код попадания
    // 3 - код промаха
    // 4 - код победы
    // 5 - код поражения

    @SuppressWarnings({"ReturnCount", "MultipleStringLiterals"})
    public static void commandSender() {
        Game gameSession = new Game();
        GameController controller = new GameController();
        while (true) {
            LOGGER.info("Guess a letter:");
            String answer = controller.userInput();
            String[] gameAnswer = gameSession.eventListener(answer).split(":");
            if ("0".equals(gameAnswer[0])) {
                return;
            }
            if ("1".equals(gameAnswer[0])) {
                LOGGER.info("Error input!");
            }
            if ("2".equals(gameAnswer[0])) {
                LOGGER.info("Hit!\n");
                LOGGER.info("The word: " + gameAnswer[1] + "\n\n");
            }
            if ("3".equals(gameAnswer[0])) {
                LOGGER.info("Missed, mistake " + gameAnswer[1] + " out of " + gameAnswer[2] + ".");
            }
            if ("4".equals(gameAnswer[0])) {
                LOGGER.info("Hit!\n");
                LOGGER.info("The word: " + gameAnswer[1] + "\n\n");
                LOGGER.info("You won!");
                return;

            }
            if ("5".equals(gameAnswer[0])) {
                LOGGER.info("Missed, mistake " + gameAnswer[1] + " out of " + gameAnswer[2] + ".");
                LOGGER.info("You lost!");
                return;
            }
        }
    }

    private String userInput() {
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
