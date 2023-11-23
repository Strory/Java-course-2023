package edu.hw7.task2;

import java.util.stream.LongStream;

public class Task2 {
    private Task2() {}

    public static long getFactorial(long n) {
        return LongStream.rangeClosed(1, n)
            .boxed()
            .toList()
            .parallelStream()
            .reduce(1L, (a, b) -> a * b);
    }
}
