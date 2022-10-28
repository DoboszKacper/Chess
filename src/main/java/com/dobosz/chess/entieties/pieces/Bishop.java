package com.dobosz.chess.entieties.pieces;

import com.dobosz.chess.entieties.Board;
import com.dobosz.chess.entieties.BoardRow;
import com.dobosz.chess.entieties.FigureColor;
import com.dobosz.chess.logic.FigureMove;
import com.dobosz.chess.logic.Orientation;
import com.dobosz.chess.logic.Position;

import java.util.Comparator;
import java.util.List;

public class Bishop extends PieceEngine implements Figure {

    public Bishop(FigureColor color, int x, int y) {
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
        for (int i = 0; i <= board.getBoardRows().size(); i++) {
            processMovements(position, i, i, Orientation.TOP_RIGHT, boardRows);
            processMovements(position, i, i, Orientation.DOWN_LEFT, boardRows);
            processMovements(position, i, i, Orientation.TOP_LEFT, boardRows);
            processMovements(position, i, i, Orientation.DOWN_RIGHT, boardRows);
        }
        //TODO: I dont think we need this when UI
        moves.sort(Comparator.comparing(move -> move.getPosition().getPositionX()));
        return moves;
    }

    @Override
    public String toString() {
        return color.getShortColor() + "B ";
    }
}
