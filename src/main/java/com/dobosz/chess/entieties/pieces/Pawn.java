package com.dobosz.chess.entieties.pieces;

import com.dobosz.chess.entieties.Board;
import com.dobosz.chess.entieties.FigureColor;
import com.dobosz.chess.logic.FigureMove;
import com.dobosz.chess.logic.MoveType;
import com.dobosz.chess.logic.Position;

import java.util.LinkedList;
import java.util.List;

import static com.dobosz.chess.utils.ChessUtils.checkIfPositionIsAllowed;
import static com.dobosz.chess.utils.ChessUtils.isEmptySpace;

public class Pawn implements Figure {

    private FigureColor color;
    private Position position;
    private boolean isInFirstTurnState;
    private List<FigureMove> moves = new LinkedList<>();

    public Pawn(FigureColor color, int x, int y) {
        this.color = color;
        position = new Position(x, y);
        isInFirstTurnState = true;
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

        //TODO Eventually to later fix with other algorithms
        int colorBased = color.equals(FigureColor.WHITE) ? -1 : 1;

        if (isInFirstTurnState) {
            FigureMove move = new FigureMove(MoveType.MOVE, position.getPositionX(), position.getPositionY() + 2 * (colorBased));
            moves.add(move);
            isInFirstTurnState = false;
        }

        int y2 = position.getPositionY() + (colorBased);
        int x2 = position.getPositionX();
        if (checkIfPositionIsAllowed(y2) && isEmptySpace(x2, y2, board.getBoardRows())) {
            FigureMove move = new FigureMove(MoveType.MOVE, x2, y2);
            moves.add(move);
        }

        int y3 = position.getPositionY() + (colorBased);
        int x3 = position.getPositionX() + 1;
        if (checkIfPositionIsAllowed(x3) && checkIfPositionIsAllowed(y3) && !isEmptySpace(x3, y3, board.getBoardRows())) {
            FigureMove attack = new FigureMove(MoveType.ATTACK, x3, y3);
            moves.add(attack);
        }

        int y4 = position.getPositionY() + (colorBased);
        int x4 = position.getPositionX() - 1;
        if (checkIfPositionIsAllowed(x4) && checkIfPositionIsAllowed(y4) && !isEmptySpace(x4, y4, board.getBoardRows())) {
            FigureMove attack = new FigureMove(MoveType.ATTACK, x4, y4);
            moves.add(attack);
        }
        return moves;
    }

    @Override
    public String toString() {
        return color.getShortColor() + "P ";
    }
}
