package com.dobosz.chess.logic;

public enum Orientation {
    TOP(0, -1),
    DOWN(0, +1),
    LEFT(-1, 0),
    RIGHT(+1, 0),
    TOP_LEFT(-1, -1),
    TOP_RIGHT(+1, -1),
    DOWN_LEFT(-1, +1),
    DOWN_RIGHT(+1, +1),
    ZERO(0, 0);

    private final int direction_x;
    private final int direction_y;

    Orientation(int x, int y) {
        this.direction_x = x;
        this.direction_y = y;
    }

    public int getDirection_x() {
        return direction_x;
    }

    public int getDirection_y() {
        return direction_y;
    }
}
