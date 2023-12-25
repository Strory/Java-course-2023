package edu.hw3.task5;

public record Contact(String firstName, String lastName) {
    @Override
    public String toString() {
        return firstName + " " + lastName;
    }
}
