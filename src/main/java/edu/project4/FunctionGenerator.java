package edu.project4;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class FunctionGenerator implements FunctionProvider {
    private final int affinFunctionsCount;
    private final int countFunctions = 5;

    public FunctionGenerator(int count) {
        this.affinFunctionsCount = count;
    }

    @SuppressWarnings("MagicNumber")
    @Override
    public AffinFunction[] getAffinFunctions() {
        Random random = new Random();
        AffinFunction[] functions = new AffinFunction[affinFunctionsCount];
        for (int i = 0; i < affinFunctionsCount; ++i) {
            while (true) {
                double a = random.nextDouble(-1, 1);
                double b = random.nextDouble(-1, 1);
                double c = random.nextDouble(-1, 1);
                double d = random.nextDouble(-1, 1);
                double e = random.nextDouble(-1, 1);
                double f = random.nextDouble(-1, 1);
                if (a * a + d * d < 1 && b * b + d * d < 1
                    && a * a + b * b + d * d + e * e < 1 + Math.pow(a * e - b * d, 2)) {
                    int red = random.nextInt(256);
                    int green = random.nextInt(256);
                    int blue = random.nextInt(256);
                    functions[i] = new AffinFunction(a, b, c, d, e, f, red, green, blue);
                    break;
                }
            }
        }
        return functions;
    }

    @SuppressWarnings("MagicNumber")
    @Override
    public double[] getNonlinearFunction(double x, double y) {
        int random = ThreadLocalRandom.current().nextInt(countFunctions);
        return switch (random) {
            case 0 -> linear(x, y);
            case 1 -> sinus(x, y);
            case 2 -> spherical(x, y);
            case 3 -> swirl(x, y);
            case 4 -> disk(x, y);
            default -> linear(x, y);
        };
    }

    public double[] linear(double x, double y) {
        return new double[] {x, y};
    }

    public double[] sinus(double x, double y) {
        return new double[] {Math.sin(x), Math.sin(y)};
    }

    public double[] spherical(double x, double y) {
        return new double[] {x / (x * x + y * y), y / (x * x + y * y)};
    }

    public double[] swirl(double x, double y) {
        double r = Math.sqrt(x * x + y * y);
        return new double[] {x * Math.sin(r * r) - y * Math.cos(r * r), x * Math.cos(r * r) + y * Math.sin(r * r)};
    }

    public double[] disk(double x, double y) {
        double r = Math.sqrt(x * x + y * y);
        double theta = Math.atan(x / y);
        return new double[] {(theta / Math.PI) * Math.sin(Math.PI * r), (theta / Math.PI) * Math.cos(Math.PI * r)};

    }
}

