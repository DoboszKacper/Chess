package com.dobosz.chess.entieties.pieces;

import com.dobosz.chess.entieties.BoardRow;
import com.dobosz.chess.entieties.FigureColor;
import com.dobosz.chess.logic.FigureMove;
import com.dobosz.chess.logic.MoveType;
import com.dobosz.chess.logic.Orientation;
import com.dobosz.chess.logic.Position;

import java.util.*;

import static com.dobosz.chess.utils.ChessUtils.checkIfPositionIsAllowed;

abstract class Piece {

    protected final FigureColor color;
    protected Position position;
    protected final List<FigureMove> moves = new LinkedList<>();
    protected final Set<Orientation> blockedOrientations = new HashSet<>();

    protected Piece(FigureColor color, int x, int y) {
        this.color = color;
        this.position = new Position(x, y);
    }

    public void processMovements(Position position, int i, int j, Orientation orientation, List<BoardRow> boardRows) {
        if (checkIfOrientationIsBlocked(orientation)) return;

        int x1 = position.getPositionX() + i * orientation.getDirection_x();
        int y1 = position.getPositionY() + j * orientation.getDirection_y();
        if (checkIfPositionIsAllowed(x1) && checkIfPositionIsAllowed(y1)) {
            addAttackOrMove(x1, y1, boardRows, orientation);
        }
    }

    private void addAttackOrMove(int x, int y, List<BoardRow> rows, Orientation orientation) {
        Figure targetFigure = rows.get(y).getFigures().get(x);

        if (checkIfSameColor(targetFigure)) {
            blockedOrientations.add(orientation);
            return;
        }

        if (checkIfEmpty(targetFigure)) {
            FigureMove move = new FigureMove(MoveType.MOVE, x, y, position);
            if (haveNotBeenLocked(move)) moves.add(move);
        }

        if (checkIfCanAttack(targetFigure)) {
            FigureMove move = new FigureMove(MoveType.ATTACK, x, y, position);
            if (haveNotBeenLocked(move)) moves.add(move);
        }
    }

    boolean checkIfCanAttack(Figure figure) {
        return !checkIfEmpty(figure);
    }

    boolean haveNotBeenLocked(FigureMove newMove) {
        return moves.stream().noneMatch(move -> move.getType().equals(MoveType.ATTACK) &&
                move.getOrientation().equals(newMove.getOrientation()));
    }

    boolean checkIfEmpty(Figure figure) {
        return figure instanceof EmptySpace;
    }

    boolean checkIfSameColor(Figure figure) {
        return figure.getColor().equals(color);
    }

    boolean checkIfOrientationIsBlocked(Orientation orientation) {
        return blockedOrientations.contains(orientation);
    }

    protected void clearBlockedOrientations() {
        blockedOrientations.clear();
    }
}

