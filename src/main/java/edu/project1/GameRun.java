package edu.project1;

public class GameRun {
    public static void main(String[] args) {
        gameRun();
    }
    public static void gameRun() {
        Game gameSession = new Game();
        gameSession.mainCycle();
    }
}
