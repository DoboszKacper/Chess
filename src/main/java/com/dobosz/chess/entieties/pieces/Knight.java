package com.dobosz.chess.entieties.pieces;

import com.dobosz.chess.entieties.Board;
import com.dobosz.chess.entieties.BoardRow;
import com.dobosz.chess.entieties.FigureColor;
import com.dobosz.chess.logic.FigureMove;
import com.dobosz.chess.logic.MoveType;
import com.dobosz.chess.logic.Orientation;
import com.dobosz.chess.logic.Position;

import java.util.LinkedList;
import java.util.List;

import static com.dobosz.chess.utils.ChessUtils.checkIfPositionIsAllowed;

public class Knight extends Piece implements Figure {

    public Knight(FigureColor color, int x, int y) {
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
        List<BoardRow> boardRows = board.getBoardRows();
        processMovements(position, 1, 2, Orientation.TOP_RIGHT, boardRows);
        processMovements(position, 2, 1, Orientation.TOP_RIGHT, boardRows);
        processMovements(position, 1, 2, Orientation.TOP_LEFT, boardRows);
        processMovements(position, 2, 1, Orientation.TOP_LEFT, boardRows);
        processMovements(position, 1, 2, Orientation.DOWN_LEFT, boardRows);
        processMovements(position, 2, 1, Orientation.DOWN_LEFT, boardRows);
        processMovements(position, 1, 2, Orientation.DOWN_RIGHT, boardRows);
        processMovements(position, 2, 1, Orientation.DOWN_RIGHT, boardRows);
        return moves;
    }

    @Override
    public String toString() {
        return color.getShortColor() + "Kn";
    }
}
