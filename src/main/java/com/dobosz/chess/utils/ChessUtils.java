package com.dobosz.chess.utils;

import com.dobosz.chess.entieties.BoardRow;
import com.dobosz.chess.entieties.FigureColor;
import com.dobosz.chess.entieties.pieces.*;
import com.dobosz.chess.logic.FigureMove;

import java.util.ArrayList;
import java.util.List;

import static com.dobosz.chess.entieties.FigureColor.WHITE;
import static com.dobosz.chess.utils.Constants.BOARD_SIZE;

public class ChessUtils {

    public static List<Figure> initStartHighFigures(FigureColor color) {
        int y = color.equals(WHITE) ? 7 : 0;
        List<Figure> highFigures = new ArrayList<>();
        highFigures.add(new Rook(color, 0, y));
        highFigures.add(new Knight(color, 1, y));
        highFigures.add(new Bishop(color, 2, y));
        highFigures.add(new Queen(color, 3, y));
        highFigures.add(new King(color, 4, y));
        highFigures.add(new Bishop(color, 5, y));
        highFigures.add(new Knight(color, 6, y));
        highFigures.add(new Rook(color, 7, y));
        return highFigures;
    }

    public static List<Figure> initStartLowFigures(FigureColor color) {
        List<Figure> lowFigures = new ArrayList<>();
        int y = color.equals(WHITE) ? 6 : 1;
        for (int i = 0; i < BOARD_SIZE; i++) {
            lowFigures.add(new Pawn(color, i, y));
        }
        return lowFigures;
    }

    public static boolean checkIfMoveIsAllowed(int x, int y, List<FigureMove> moves) {
        for (FigureMove move : moves)
            if (move.getPosition().getPositionX() == x && move.getPosition().getPositionY() == y) return true;
        return false;
    }

    public static boolean isEmptySpace(int x, int y, List<BoardRow> boardRows) {
        final Figure targetFigure = boardRows.get(y).getFigures().get(x);
        return (targetFigure instanceof EmptySpace);
    }

    public static boolean checkIfPositionIsAllowed(int a) {
        return (0 <= a && a <= 7);
    }
}
