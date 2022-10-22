package com.dobosz.chess.logic;

public class FigureMove {
    private final MoveType type;
    private final Position position;
    private final Orientation orientation;

    public FigureMove(MoveType type, int x, int y) {
        this.type = type;
        this.position = new Position(x, y);
        //TODO Take care of this
        orientation = Orientation.ZERO;
    }

    public FigureMove(MoveType type, int x, int y, Position previousPosition) {
        this.type = type;
        this.position = new Position(x, y);
        orientation = calculateOrientation(x, y, previousPosition);
    }

    private Orientation calculateOrientation(int x, int y, Position previousPosition) {
        Orientation orientation = Orientation.ZERO;

        if (y > previousPosition.getPositionY() && x == previousPosition.getPositionX()) {
            orientation = Orientation.DOWN;
        } else if (y < previousPosition.getPositionY() && x == previousPosition.getPositionX()) {
            orientation = Orientation.TOP;
        } else if (y == previousPosition.getPositionY() && x > previousPosition.getPositionX()) {
            orientation = Orientation.RIGHT;
        } else if (y == previousPosition.getPositionY() && x < previousPosition.getPositionX()) {
            orientation = Orientation.LEFT;
        } else if (y > previousPosition.getPositionY() && x > previousPosition.getPositionX()) {
            orientation = Orientation.DOWN_RIGHT;
        } else if (y < previousPosition.getPositionY() && x > previousPosition.getPositionX()) {
            orientation = Orientation.TOP_RIGHT;
        } else if (y > previousPosition.getPositionY() && x > previousPosition.getPositionX()) {
            orientation = Orientation.DOWN_LEFT;
        } else if (y < previousPosition.getPositionY() && x < previousPosition.getPositionX()) {
            orientation = Orientation.TOP_LEFT;
        }
        return orientation;
    }

    public Orientation getOrientation() {
        return orientation;
    }

    public MoveType getType() {
        return type;
    }

    public Position getPosition() {
        return position;
    }

    @Override
    public String toString() {
        return "Move Type: " + type + " Destination: " + position + "\n";
    }
}
