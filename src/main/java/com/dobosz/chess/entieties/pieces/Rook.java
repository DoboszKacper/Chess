package com.dobosz.chess.entieties.pieces;

import com.dobosz.chess.entieties.Board;
import com.dobosz.chess.entieties.BoardRow;
import com.dobosz.chess.entieties.FigureColor;
import com.dobosz.chess.logic.FigureMove;
import com.dobosz.chess.logic.MoveType;
import com.dobosz.chess.logic.Orientation;
import com.dobosz.chess.logic.Position;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import static com.dobosz.chess.utils.ChessUtils.checkIfPositionIsAllowed;

public class Rook extends Piece implements Figure {

    public Rook(FigureColor color, int x, int y) {
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
        for (int i = 1; i <= board.getBoardRows().size(); i++) {
            processMovements(position, i, i, Orientation.TOP, boardRows);
            processMovements(position, i, i, Orientation.DOWN, boardRows);
            processMovements(position, i, i, Orientation.RIGHT, boardRows);
            processMovements(position, i, i, Orientation.LEFT, boardRows);
        }
        moves.sort(Comparator.comparing(move -> move.getPosition().getPositionX()));
        clearBlockedOrientations();
        return moves;
    }

    @Override
    public String toString() {
        return color.getShortColor() + "R ";
    }
}
