package edu.project4;

public class Pixel {
    private int red;
    private int green;
    private int blue;
    private int hitCount;
    private double normal;

    public Pixel(int r, int g, int b, int hitCount) {
        this.red = r;
        this.green = g;
        this.blue = b;
        this.hitCount = hitCount;
    }

    public int getRed() {
        return red;
    }

    public int getGreen() {
        return green;
    }

    public int getBlue() {
        return blue;
    }

    public int getHitCount() {
        return hitCount;
    }

    public double getNormal() {
        return normal;
    }

    public void setRed(int r) {
        this.red = r;
    }

    public void setGreen(int g) {
        this.green = g;
    }

    public void setBlue(int b) {
        this.blue = b;
    }

    public void setHitCount(int hitCount) {
        this.hitCount = hitCount;
    }

    public void hitCountIncrement() {
        this.hitCount++;
    }

    public void setNormal(double normal) {
        this.normal = normal;
    }
}

