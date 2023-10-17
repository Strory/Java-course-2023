package edu.hw1;

public class Task1 {
    public static void main(String[] args) {
        String inputTimeString = "20:22";
        System.out.println(len_video(inputTimeString));
    }

    public static int len_video(String time) {
        final int secInMin = 60;
        String[] times = time.split(":");
        int min = Integer.parseInt(times[0]);
        int clear_sec = Integer.parseInt(times[1]);
        if (clear_sec > 59 || clear_sec < 0 || min < 0) {
            return -1;
        }
        return min * secInMin + clear_sec;
    }
}

