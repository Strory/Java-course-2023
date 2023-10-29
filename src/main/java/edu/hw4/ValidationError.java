package edu.hw4;

import java.util.HashSet;
import java.util.Set;

public class ValidationError {
    private static final int AGELIMIT = 60;
    private static final int HEIGHTLIMIT = 100;
    private static final int WEIGHTLIMIT = 200;
    private String errorText;

    private ValidationError(String text) {
        errorText = text;
    }

    public String getError() {
        return errorText;
    }

    public static Set<ValidationError> checkValidate(Animal animal) {
        Set<ValidationError> errors = new HashSet<>();
        if (!checkAge(animal.age())) {
            errors.add(new ValidationError("age error"));
        }
        if (!checkHeight(animal.height())) {
            errors.add(new ValidationError("height error"));
        }
        if (!checkWeight(animal.weight())) {
            errors.add(new ValidationError("weight error"));
        }
        return errors;
    }

    public static boolean checkAge(int age) {
        return age >= 0 && age <= AGELIMIT;
    }

    public static boolean checkHeight(int height) {
        return height > 0 && height <= HEIGHTLIMIT;
    }

    public static boolean checkWeight(int weight) {
        return weight > 0 && weight <= WEIGHTLIMIT;
    }
}
