package com.dobosz.chess.logic;

import java.util.Arrays;

import static com.dobosz.chess.utils.ChessUtils.checkIfPositionIsAllowed;

public class Position {
    private int[] position = new int[2];

    public Position(int x, int y) {
        if (checkIfPositionIsAllowed(x) && checkIfPositionIsAllowed(y)) {
            this.position[0] = x;
            this.position[1] = y;
        }
    }

    public int[] getPosition() {
        return position;
    }

    public int getPositionX() {
        return position[0];
    }

    public int getPositionY() {
        return position[1];
    }

    public void setPosition(int x, int y) {
        if (checkIfPositionIsAllowed(x) && checkIfPositionIsAllowed(y)) {
            this.position[0] = x;
            this.position[1] = y;
        }
    }

    @Override
    public String toString() {
        return "Position=" + Arrays.toString(position);
    }
}
