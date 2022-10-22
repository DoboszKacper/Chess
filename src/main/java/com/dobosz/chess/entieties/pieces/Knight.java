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

public class Knight implements Figure {

    private final FigureColor color;
    private Position position;
    private List<FigureMove> moves = new LinkedList<>();

    public Knight(FigureColor color, int x, int y) {
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

    private void processMovements(Position position, int i, int j, Orientation orientation, List<BoardRow> boardRows) {
        int x1 = position.getPositionX() + i * orientation.getDirection_x();
        int y1 = position.getPositionY() + j * orientation.getDirection_y();
        if (checkIfPositionIsAllowed(x1) && checkIfPositionIsAllowed(y1)) {
            addAttackOrMove(x1, y1, boardRows);
        }
    }

    private void addAttackOrMove(int x, int y, List<BoardRow> rows) {
        if (checkIfEmpty(rows.get(y).getFigures().get(x))) {
            FigureMove move = new FigureMove(MoveType.MOVE, x, y, position);
            if (haveNotAttackThisDirections(move)) moves.add(move);
        }

        if (checkIfCanAttack(rows.get(y).getFigures().get(x))) {
            FigureMove move = new FigureMove(MoveType.ATTACK, x, y, position);
            if (haveNotAttackThisDirections(move)) moves.add(move);
        }
    }

    boolean checkIfCanAttack(Figure figure) {
        return !checkIfEmpty(figure) &&
                !checkIfSameColor(figure);
    }

    boolean haveNotAttackThisDirections(FigureMove newMove) {
        return moves.stream().noneMatch(move -> move.getType().equals(MoveType.ATTACK) && move.getOrientation().equals(newMove.getOrientation()));
    }

    boolean checkIfEmpty(Figure figure) {
        return figure instanceof EmptySpace;
    }

    boolean checkIfSameColor(Figure figure) {
        return figure.getColor().equals(this.getColor());
    }

    @Override
    public String toString() {
        return color.getShortColor() + "Kn";
    }
}
