package edu.hw10.task2;

public class Console {

    private Console() {
    }

    @SuppressWarnings("MagicNumber")
    public static void example() {
        FibCalculator c = new SomeClass();
        FibCalculator proxy = CacheProxy.create(c, c.getClass());

        proxy.fib(23);
    }
}
