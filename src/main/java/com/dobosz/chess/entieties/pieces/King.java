package com.dobosz.chess.entieties.pieces;

import com.dobosz.chess.entieties.Board;
import com.dobosz.chess.entieties.BoardRow;
import com.dobosz.chess.entieties.FigureColor;
import com.dobosz.chess.logic.FigureMove;
import com.dobosz.chess.logic.Orientation;
import com.dobosz.chess.logic.Position;

import java.util.List;

public class King extends Piece implements Figure {

    public King(FigureColor color, int x, int y) {
        super(color, x, y);
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
        Position position = getPosition();
        moves.clear();
        //TODO implement king vs king and 'roszade'
        List<BoardRow> boardRows = board.getBoardRows();
        processMovements(position, 1, 1, Orientation.TOP, boardRows);
        processMovements(position, 1, 1, Orientation.DOWN, boardRows);
        processMovements(position, 1, 1, Orientation.RIGHT, boardRows);
        processMovements(position, 1, 1, Orientation.LEFT, boardRows);
        processMovements(position, 1, 1, Orientation.TOP_RIGHT, boardRows);
        processMovements(position, 1, 1, Orientation.DOWN_LEFT, boardRows);
        processMovements(position, 1, 1, Orientation.TOP_LEFT, boardRows);
        processMovements(position, 1, 1, Orientation.DOWN_RIGHT, boardRows);
        clearBlockedOrientations();
        return moves;
    }

    @Override
    public String toString() {
        return color.getShortColor() + "Ki";
    }
}
