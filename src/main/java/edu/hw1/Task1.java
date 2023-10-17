package edu.hw1;

public class Task1 {

    private Task1() {
    }

    public static int lenVideo(String time) {
        final int secInMin = 60;
        final int secLimit = 59;
        String[] times = time.split(":");
        int min = Integer.parseInt(times[0]);
        int clearSec = Integer.parseInt(times[1]);
        if (clearSec > secLimit || clearSec < 0 || min < 0) {
            return -1;
        }
        return min * secInMin + clearSec;
    }
}

