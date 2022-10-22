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

public class Rook implements Figure {

    private FigureColor color;
    private Position position;
    private List<FigureMove> moves;

    public Rook(FigureColor color, int x, int y) {
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
        moves = new LinkedList<>();
//        List<Figure> figuresVertically = board.getBoardRows().get(position.getPositionY()).getFigures();
//        processMovements(figuresVertically, position.getPositionY(), position.getPositionX());
//
//        List<Figure> figuresHorizontally = board.getBoardRows().stream().map(row -> row.getFigures().get(position.getPositionX())).collect(Collectors.toList());
//        processMovements(figuresHorizontally, position.getPositionX(), position.getPositionY());

        List<BoardRow> boardRows = board.getBoardRows();
        for (int i = 0; i <= board.getBoardRows().size(); i++) {
            processMovements(position, i, Orientation.TOP, boardRows);
            processMovements(position, i, Orientation.DOWN, boardRows);
            processMovements(position, i, Orientation.RIGHT, boardRows);
            processMovements(position, i, Orientation.LEFT, boardRows);
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

//    private void processMovements(List<Figure> figures, int constant, int modifiable) {
//        boolean firstLock = false;
//        boolean secondLock = false;
//        for (int i = 1, figuresSize = figures.size(); i <= figuresSize; i++) {
//            int x = modifiable - i;
//            int y = modifiable + i;
//
//            if (checkIfPositionIsAllowed(x)) {
//                if (checkIfSameColor(figures.get(x))) firstLock = true;
//
//                if (!firstLock) addAttackOrMove(constant, x, figures);
//            }
//            if (checkIfPositionIsAllowed(y)) {
//                if (checkIfSameColor(figures.get(y))) secondLock = true;
//
//                if (!secondLock) addAttackOrMove(constant, y, figures);
//            }
//        }
//    }
//
//    private void addAttackOrMove(int constant, int modifiable, List<Figure> figures) {
//        if (checkIfEmpty(figures.get(modifiable))) {
//            FigureMove move = new FigureMove(MoveType.MOVE, constant, modifiable, position);
//            if (haveNotAttackThisDirections(move)) moves.add(move);
//        }
//
//        if (checkIfCanAttack(figures, modifiable)) {
//            FigureMove move = new FigureMove(MoveType.ATTACK, constant, modifiable, position);
//            if (haveNotAttackThisDirections(move)) moves.add(move);
//        }
//    }

    boolean checkIfCanAttack(Figure figure) {
        return !checkIfEmpty(figure) &&
                !checkIfSameColor(figure);
    }

//    boolean checkIfCanAttack(List<Figure> figures, int targetIndex) {
//        return !checkIfEmpty(figures.get(targetIndex)) &&
//                !checkIfSameColor(figures.get(targetIndex));
//    }

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
        return color.getShortColor() + "R ";
    }
}
