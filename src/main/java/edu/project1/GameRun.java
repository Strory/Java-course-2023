package edu.project1;

public class GameRun {
    private GameRun() {
    }

    public static void gameRun() {
        Game gameSession = new Game();
        gameSession.mainCycle();
    }
}
