package com.dobosz.chess.entieties.pieces;

import com.dobosz.chess.entieties.Board;
import com.dobosz.chess.entieties.BoardRow;
import com.dobosz.chess.entieties.FigureColor;
import com.dobosz.chess.logic.FigureMove;
import com.dobosz.chess.logic.Orientation;
import com.dobosz.chess.logic.Position;

import java.util.LinkedList;
import java.util.List;

public class Pawn extends PieceEngine implements Figure {

    private boolean isInFirstTurnState;
    private List<FigureMove> moves = new LinkedList<>();

    public Pawn(FigureColor color, int x, int y) {
        super(color, x, y);
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

//    @Override
//    public List<FigureMove> getAllMovements(Board board) {
//        Position position = getPosition();
//        moves.clear();
//
//        //TODO Eventually to later fix with other algorithms
//        int colorBased = color.equals(FigureColor.WHITE) ? -1 : 1;
//
//        if (isInFirstTurnState) {
//            FigureMove move = new FigureMove(MoveType.MOVE, position.getPositionX(), position.getPositionY() + 2 * (colorBased));
//            moves.add(move);
//            isInFirstTurnState = false;
//        }
//
//        int y2 = position.getPositionY() + (colorBased);
//        int x2 = position.getPositionX();
//        if (checkIfPositionIsAllowed(y2) && isEmptySpace(x2, y2, board.getBoardRows())) {
//            FigureMove move = new FigureMove(MoveType.MOVE, x2, y2);
//            moves.add(move);
//        }
//
//        int y3 = position.getPositionY() + (colorBased);
//        int x3 = position.getPositionX() + 1;
//        if (checkIfPositionIsAllowed(x3) && checkIfPositionIsAllowed(y3) && !isEmptySpace(x3, y3, board.getBoardRows())) {
//            FigureMove attack = new FigureMove(MoveType.ATTACK, x3, y3);
//            moves.add(attack);
//        }
//
//        int y4 = position.getPositionY() + (colorBased);
//        int x4 = position.getPositionX() - 1;
//        if (checkIfPositionIsAllowed(x4) && checkIfPositionIsAllowed(y4) && !isEmptySpace(x4, y4, board.getBoardRows())) {
//            FigureMove attack = new FigureMove(MoveType.ATTACK, x4, y4);
//            moves.add(attack);
//        }
//        return moves;
//    }


    @Override
    public List<FigureMove> getAllMovements(Board board) {
        Position position = getPosition();
        moves.clear();
        int colorBased = color.equals(FigureColor.WHITE) ? -1 : 1;
        //TODO implement king vs king and 'roszade'
        List<BoardRow> boardRows = board.getBoardRows();
        if (isInFirstTurnState) {
            processMovements(position, colorBased * 2, colorBased * 2, Orientation.TOP, boardRows);
            processMovements(position, colorBased * 2, colorBased * 2, Orientation.DOWN, boardRows);
            isInFirstTurnState = false;
        }
        processMovements(position, colorBased, colorBased, Orientation.TOP, boardRows);
        processMovements(position, colorBased, colorBased, Orientation.DOWN, boardRows);
        processMovements(position, colorBased, colorBased, Orientation.TOP_RIGHT, boardRows);
        processMovements(position, colorBased, colorBased, Orientation.DOWN_LEFT, boardRows);
        processMovements(position, colorBased, colorBased, Orientation.TOP_LEFT, boardRows);
        processMovements(position, colorBased, colorBased, Orientation.DOWN_RIGHT, boardRows);
        clearBlockedOrientations();
        return moves;
    }

    @Override
    public String toString() {
        return color.getShortColor() + "P ";
    }
}
