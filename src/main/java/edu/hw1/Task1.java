package edu.hw1;

public class Task1 {

    private Task1() {
    }

    public static int lenVideo(String time) {
        final int secInMin = 60;
        final int secLimit = 59;
        String[] times = time.split(":");
        int minutes = Integer.parseInt(times[0]);
        int clearSeconds = Integer.parseInt(times[1]);
        if (clearSeconds > secLimit || clearSeconds < 0 || minutes < 0) {
            return -1;
        }
        return minutes * secInMin + clearSeconds;
    }
}

