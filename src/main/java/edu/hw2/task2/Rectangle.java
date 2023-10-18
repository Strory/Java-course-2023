package edu.hw2.task2;

public class Rectangle {
    private int width;
    private int height;

    public boolean setWidth(int width) {
        this.width = width;
        return true;
    }

    public boolean setHeight(int height) {
        this.height = height;
        return true;
    }

    public double area() {
        return width * height;
    }
}
