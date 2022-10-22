package com.dobosz.chess.entieties.pieces;

import com.dobosz.chess.entieties.Board;
import com.dobosz.chess.entieties.FigureColor;
import com.dobosz.chess.logic.FigureMove;
import com.dobosz.chess.logic.Position;

import java.util.List;

public class EmptySpace implements Figure {

    private Position position;

    public EmptySpace(int x, int y) {
        this.position = new Position(x, y);
    }

    @Override
    public FigureColor getColor() {
        return FigureColor.NONE;
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
        return FigureColor.NONE.getShortColor();
    }
}
