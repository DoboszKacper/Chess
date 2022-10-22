//package com.dobosz.chess.logic;
//
//import com.dobosz.chess.entieties.BoardRow;
//import com.dobosz.chess.entieties.pieces.EmptySpace;
//import com.dobosz.chess.entieties.pieces.Figure;
//
//import java.util.LinkedList;
//import java.util.List;
//
//import static com.dobosz.chess.utils.ChessUtils.checkIfPositionIsAllowed;
//
//public class MoveEngine {
//
//    private List<FigureMove> moves = new LinkedList<>();
//
//    private void processMovements(Position position, int i, int j, Orientation orientation, List<BoardRow> boardRows) {
//        int x1 = position.getPositionX() + i * orientation.getDirection_x();
//        int y1 = position.getPositionY() + j * orientation.getDirection_y();
//        if (checkIfPositionIsAllowed(x1) && checkIfPositionIsAllowed(y1)) {
//            addAttackOrMove(x1, y1, boardRows);
//        }
//    }
//
//    private void addAttackOrMove(int x, int y, List<BoardRow> rows) {
//        if (checkIfEmpty(rows.get(y).getFigures().get(x))) {
//            FigureMove move = new FigureMove(MoveType.MOVE, x, y, position);
//            if (haveNotAttackThisDirections(move)) moves.add(move);
//        }
//
//        if (checkIfCanAttack(rows.get(y).getFigures().get(x))) {
//            FigureMove move = new FigureMove(MoveType.ATTACK, x, y, position);
//            if (haveNotAttackThisDirections(move)) moves.add(move);
//        }
//    }
//
//    boolean checkIfCanAttack(Figure figure) {
//        return !checkIfEmpty(figure) &&
//                !checkIfSameColor(figure);
//    }
//
//    boolean haveNotAttackThisDirections(FigureMove newMove) {
//        return moves.stream().noneMatch(move -> move.getType().equals(MoveType.ATTACK) && move.getOrientation().equals(newMove.getOrientation()));
//    }
//
//    boolean checkIfEmpty(Figure figure) {
//        return figure instanceof EmptySpace;
//    }
//
//    boolean checkIfSameColor(Figure figure) {
//        return figure.getColor().equals(this.getColor());
//    }
//}
