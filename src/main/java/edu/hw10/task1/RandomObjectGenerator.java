package edu.hw10.task1;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Random;
import java.util.UUID;
import java.util.logging.Logger;

public class RandomObjectGenerator {

    private final int minInt = -1_000_000;
    private final int maxInt = 1_000_000;
    private Logger logger = Logger.getLogger("RandomObjectGenerator");
    private Random random = new Random();

    public RandomObjectGenerator() {
    }

    public <T> T nextObject(Class<T> inputClass) {
        try {
            Constructor<?> constructor = inputClass.getDeclaredConstructors()[0];

            Parameter[] constructorParameters = constructor.getParameters();
            Object[] parameters = new Object[constructorParameters.length];

            for (int i = 0; i < constructorParameters.length; i++) {
                parameters[i] = generateValueForParameter(constructorParameters[i]);
            }
            return (T) constructor.newInstance(parameters);
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException e) {
            logger.severe(e.getMessage());
            return null;
        }
    }

    public <T> T nextObject(Class<T> inputClass, String factoryMethodName) {
        try {
            Method factoryMethod = inputClass.getDeclaredMethod(factoryMethodName);

            Parameter[] constructorParameters = factoryMethod.getParameters();
            Object[] parameters = new Object[constructorParameters.length];

            for (int i = 0; i < constructorParameters.length; i++) {
                parameters[i] = generateValueForParameter(constructorParameters[i]);
            }
            return (T) factoryMethod.invoke(null, parameters);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            logger.severe(e.getMessage());
            return null;
        }
    }

    @SuppressWarnings("ReturnCount")
    private Object generateValueForParameter(Parameter parameter) {
        Class<?> type = parameter.getType();

        if (type == int.class || type == Integer.class) {
            return generateRandomIntValue(parameter);
        } else if (type == long.class || type == Long.class) {
            return generateRandomLongValue(parameter);
        } else if (type == double.class || type == Double.class) {
            return generateRandomDoubleValue(parameter);
        } else if (type == boolean.class || type == Boolean.class) {
            return generateRandomBooleanValue(parameter);
        } else if (type == String.class) {
            return generateRandomStringValue(parameter);
        } else {
            logger.info("Unsupported parameter");
            return null;
        }
    }

    private int generateRandomIntValue(Parameter parameter) {
        Min minAnnotation = parameter.getAnnotation(Min.class);
        Max maxAnnotation = parameter.getAnnotation(Max.class);

        int minValue = (minAnnotation != null) ? minAnnotation.value() : minInt;
        int maxValue = (maxAnnotation != null) ? maxAnnotation.value() : maxInt;

        return random.nextInt(minValue, maxValue);
    }

    private long generateRandomLongValue(Parameter parameter) {
        Min minAnnotation = parameter.getAnnotation(Min.class);
        Max maxAnnotation = parameter.getAnnotation(Max.class);

        long minValue = (minAnnotation != null) ? minAnnotation.value() : minInt;
        long maxValue = (maxAnnotation != null) ? maxAnnotation.value() : maxInt;

        return random.nextLong(minValue, maxValue);
    }

    private double generateRandomDoubleValue(Parameter parameter) {
        Min minAnnotation = parameter.getAnnotation(Min.class);
        Max maxAnnotation = parameter.getAnnotation(Max.class);

        double minValue = (minAnnotation != null) ? minAnnotation.value() : minInt;
        double maxValue = (maxAnnotation != null) ? minAnnotation.value() : maxInt;

        return random.nextDouble(minValue, maxValue);
    }

    private boolean generateRandomBooleanValue(Parameter parameter) {
        return random.nextBoolean();
    }

    private String generateRandomStringValue(Parameter parameter) {
        return UUID.randomUUID().toString();
    }
}
