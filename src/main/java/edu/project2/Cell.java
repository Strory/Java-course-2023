package edu.project2;

public class Cell {
    private boolean wallRight;
    private boolean wallDown;
    private int setId;

    Cell() {
        wallRight = false;
        wallDown = false;
        setId = -1;
    }

    public int getId() {
        return setId;
    }

    public boolean getWallDown() {
        return wallDown;
    }

    public boolean getWallRight() {
        return wallRight;
    }

    public void setWallRight(boolean r) {
        wallRight = r;
    }

    public void setWallDown(boolean d) {
        wallDown = d;
    }

    public void setId(int id) {
        setId = id;
    }
}
