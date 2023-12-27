package edu.hw10.task1;

import java.util.logging.Logger;

public class Console {

    public static Logger logger = Logger.getLogger("Console");

    private Console() {}

    public static void show() {
        RandomObjectGenerator rog = new RandomObjectGenerator();

        try {
            Person person = rog.nextObject(Person.class);
            Coordinate coordinate = rog.nextObject(Coordinate.class);

            logger.info("Возраст: " + person.getAge());
            logger.info("Поле row: " + coordinate.row());
        } catch (Exception e) {
            logger.severe(e.getMessage());
        }
    }
}
