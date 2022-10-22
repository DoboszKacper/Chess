package com.dobosz.chess.entieties;

import com.dobosz.chess.entieties.pieces.EmptySpace;
import com.dobosz.chess.entieties.pieces.Figure;
import com.dobosz.chess.logic.FigureMove;
import com.dobosz.chess.utils.ChessUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static com.dobosz.chess.entieties.FigureColor.BLACK;
import static com.dobosz.chess.entieties.FigureColor.WHITE;
import static com.dobosz.chess.logic.MoveType.ATTACK;
import static com.dobosz.chess.utils.Constants.BOARD_SIZE;

@Component
public class Board {
    private final List<BoardRow> boardRows = new ArrayList<>();

    public Board() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            boardRows.add(new BoardRow(i));
        }
    }

    public List<BoardRow> getBoardRows() {
        return boardRows;
    }

    public void init() {
        boardRows.set(0, new BoardRow(ChessUtils.initStartHighFigures(BLACK)));
//        boardRows.set(1, new BoardRow(ChessUtils.initStartLowFigures(BLACK)));
//        boardRows.set(6, new BoardRow(ChessUtils.initStartLowFigures(WHITE)));
        boardRows.set(7, new BoardRow(ChessUtils.initStartHighFigures(WHITE)));
    }

    public Figure getFigure(int x, int y) throws Exception {
        Figure figure = boardRows.get(y).getFigures().get(x);

        //TODO Can be deleted or edited when shifted to UI
        if (figure instanceof EmptySpace) {
            throw new Exception("You cant only chose figures!");
        }
        return figure;
    }

    public void moveFigure(FigureMove move, Figure figure) {
        int oldPositionX = figure.getPosition().getPosition()[0];
        int oldPositionY = figure.getPosition().getPosition()[1];
        int newPositionX = move.getPosition().getPosition()[0];
        int newPositionY = move.getPosition().getPosition()[1];

        if (move.getType().equals(ATTACK)) {
            Figure attackedFigure = boardRows.get(oldPositionY).getFigures().get(oldPositionX);
            System.out.println(attackedFigure + "has been attacked and killed!");
        }
        figure.getPosition().setPosition(newPositionX, newPositionY);
        boardRows.get(newPositionY).getFigures().set(newPositionX, figure);
        boardRows.get(oldPositionY).getFigures().set(oldPositionX, new EmptySpace(oldPositionX, oldPositionY));
        // TODO if killed;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("CHESS BOARD\n");
        s.append("_____0____1____2____3____4____5____6____7__\n");
        int i = 0;
        for (BoardRow boardRow : boardRows) {
            s.append(i).append(" ").append(boardRow);
            i++;
        }
        return s.toString();
    }
}
