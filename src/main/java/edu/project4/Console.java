package edu.project4;

import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Logger;

public class Console {

    public static Logger logger = Logger.getLogger("Console");

    private Console() {}

    @SuppressWarnings("MagicNumber")
    public static void generateImages() {
        for (int i = 0; i < 10; ++i) {
            int affinCount = ThreadLocalRandom.current().nextInt(1, 10);
            int symmetry = ThreadLocalRandom.current().nextInt(2);
            double gamma = ThreadLocalRandom.current().nextDouble(0.2, 2.3);


            FunctionProvider provider = new FunctionGenerator(affinCount);
            Renderer renderer = new ManyThreadRenderer(provider, 4);
            renderer.render(10000, 5000, 1920, 1080, symmetry,
                gamma, ImageFormat.PNG, "fractalThread" + i);
        }
    }

    @SuppressWarnings({"MagicNumber", "MultipleVariableDeclarations"})
    public static void measureTheTime() {
        long startTime, endTime, executionTime;

        startTime = System.nanoTime();
        for (int i = 0; i < 20; ++i) {
            FunctionProvider provider = new FunctionGenerator(5);
            Renderer renderer = new OneThreadRenderer(provider);
            renderer.render(10000, 5000, 1500, 700, 0,
                1.8, ImageFormat.PNG, "fractal_1" + i);
        }
        endTime = System.nanoTime();
        executionTime = endTime - startTime;
        logger.info(String.valueOf(executionTime / Math.pow(10, 9)));


        startTime = System.nanoTime();
        for (int i = 0; i < 20; ++i) {
            FunctionProvider provider = new FunctionGenerator(5);
            Renderer renderer = new ManyThreadRenderer(provider, 4);
            renderer.render(10000, 5000, 1500, 700, 0,
                1.8, ImageFormat.PNG, "fractalThread_1" + i);
        }
        endTime = System.nanoTime();
        executionTime = endTime - startTime;
        logger.info(String.valueOf(executionTime / Math.pow(10, 9)));
    }
}
