package com.dobosz.chess.entieties.pieces;

import com.dobosz.chess.entieties.Board;
import com.dobosz.chess.entieties.BoardRow;
import com.dobosz.chess.entieties.FigureColor;
import com.dobosz.chess.logic.FigureMove;
import com.dobosz.chess.logic.Orientation;
import com.dobosz.chess.logic.Position;

import java.util.Comparator;
import java.util.List;

public class Queen extends PieceEngine implements Figure {

    public Queen(FigureColor color, int x, int y) {
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
            processMovements(position, i, i, Orientation.TOP_RIGHT, boardRows);
            processMovements(position, i, i, Orientation.DOWN_LEFT, boardRows);
            processMovements(position, i, i, Orientation.TOP_LEFT, boardRows);
            processMovements(position, i, i, Orientation.DOWN_RIGHT, boardRows);
        }
        moves.sort(Comparator.comparing(move -> move.getPosition().getPositionX()));
        return moves;
    }


    @Override
    public String toString() {
        return color.getShortColor() + "Q ";
    }
}
