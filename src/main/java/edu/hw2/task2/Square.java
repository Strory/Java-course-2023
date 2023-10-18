package edu.hw2.task2;

public class Square extends Rectangle {
    @Override
    public boolean setWidth(int width) {
        super.setWidth(width);
        super.setHeight(width);
        return true;
    }

    @Override
    public boolean setHeight(int height) {
        return false;
    }
}
