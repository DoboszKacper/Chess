package com.dobosz.chess.entieties.pieces;

import com.dobosz.chess.entieties.Board;
import com.dobosz.chess.entieties.BoardRow;
import com.dobosz.chess.entieties.FigureColor;
import com.dobosz.chess.logic.FigureMove;
import com.dobosz.chess.logic.MoveType;
import com.dobosz.chess.logic.Orientation;
import com.dobosz.chess.logic.Position;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static com.dobosz.chess.utils.ChessUtils.checkIfPositionIsAllowed;

public class Queen implements Figure {

    private final FigureColor color;
    private Position position;
    private final List<FigureMove> moves = new ArrayList<>();

    public Queen(FigureColor color, int x, int y) {
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
        //TODO implement king vs king and 'roszade'
        List<BoardRow> boardRows = board.getBoardRows();
        for (int i = 0; i <= board.getBoardRows().size(); i++) {
            processMovements(position, i, Orientation.TOP, boardRows);
            processMovements(position, i, Orientation.DOWN, boardRows);
            processMovements(position, i, Orientation.RIGHT, boardRows);
            processMovements(position, i, Orientation.LEFT, boardRows);
            processMovements(position, i, Orientation.TOP_RIGHT, boardRows);
            processMovements(position, i, Orientation.DOWN_LEFT, boardRows);
            processMovements(position, i, Orientation.TOP_LEFT, boardRows);
            processMovements(position, i, Orientation.DOWN_RIGHT, boardRows);
        }
        moves.sort(Comparator.comparing(move -> move.getPosition().getPositionX()));
        return moves;
    }

    private void processMovements(Position position, int i, Orientation orientation, List<BoardRow> boardRows) {
        int x1 = position.getPositionX() + i * orientation.getDirection_x();
        int y1 = position.getPositionY() + i * orientation.getDirection_y();
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
        return color.getShortColor() + "Q ";
    }
}
