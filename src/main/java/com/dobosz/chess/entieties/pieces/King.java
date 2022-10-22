package com.dobosz.chess.entieties.pieces;

import com.dobosz.chess.entieties.Board;
import com.dobosz.chess.entieties.FigureColor;
import com.dobosz.chess.logic.FigureMove;
import com.dobosz.chess.logic.Position;

import java.util.List;

public class King implements Figure {

    private final FigureColor color;
    private Position position;

    public King(FigureColor color, int x, int y) {
        this.color = color;
        this.position = new Position(x, y);
    }

    @Override
    public FigureColor getColor() {
        return color;
    }

    @Override
    public Position getPosition() {
        return position;
    }

    @Override
    public List<FigureMove> getAllMovements(Board board) {
        return null;
    }

    @Override
    public String toString() {
        return color.getShortColor() + "Ki";
    }
}
